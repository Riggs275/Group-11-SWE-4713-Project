package com.accountingAPI.accountingSoftware.service;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.Passwords;
import com.accountingAPI.accountingSoftware.model.RequestedUser;
import com.accountingAPI.accountingSoftware.model.Users;
import com.accountingAPI.accountingSoftware.repository.PasswordRepository;
import com.accountingAPI.accountingSoftware.repository.RequestedUserRepository;
import com.accountingAPI.accountingSoftware.repository.UserRepository;
import com.accountingAPI.accountingSoftware.util.PasswordGenerator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRepository passwordRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RequestedUserRepository requestedUserRepository;

    public ResponseEntity<?> loginUser(Map<String, String> loginData) {
        String userNameid = loginData.get("userID");
        String sentPassword = loginData.get("password");
        sentPassword = PasswordGenerator.hashPassword(sentPassword);
        Optional<Users> user = userRepository.findByUserID(userNameid);

        System.out.println("Received user_nameid: " + userNameid);
        System.out.println("Received password: " + sentPassword);
        System.out.print("USER" + user.toString());

        if (user.isPresent()) {

            Users userRec = user.get();
            if (!userRec.getActive())
                return ResponseEntity.badRequest().body(Map.of("error", "Your account is disabled contact your admin to reactivate"));

            Optional<Passwords> pass = passwordRepository.findById(userRec.getPassRef()); 
            

            System.out.print(pass.toString());

            if (pass.isPresent()) {
                Passwords passRec = pass.get();
                if(passRec.getPasswordHash().equals(sentPassword)){
                    return ResponseEntity.ok().body(Map.of("message", "Login successful", "userType", userRec.getUserType(), "firstName",userRec.getFirstName(), "lastName", userRec.getLastName()));
                }else{
                    return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
                }
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
        }
    }


    public ResponseEntity<?> forgotPassword(Map<String,String> forgotPasswordData){
        String email = forgotPasswordData.get("email");
        String userID = forgotPasswordData.get("userID");
        Optional<Users> user = userRepository.findByUserID(userID);
        System.out.println("Recieved user_id: " + userID);
        System.out.println("USER: " + (user.isPresent() ? user.get().toString() : "User not found"));
        if(user.isPresent()){
            if(user.get().getEmail().equalsIgnoreCase(email)){
                return ResponseEntity.ok().body(Map.of("message", "email found","userID", userID, "userQ1", user.get().getSecurityQ()));
            }else{
                return ResponseEntity.badRequest().body(Map.of("error", "Could not find ID or email"));
            }
        }else{
            return ResponseEntity.badRequest().body(Map.of("error", "Could not find ID or email"));
        }
    }
    //Only checks answer 1 currently but soon will do better
    public ResponseEntity<?> securityQuestions(Map<String,String> securityData, String userID){
        String answer1 = securityData.get("sec1");


        Optional<Users> user = userRepository.findByUserID(userID);
        if(user.isPresent()){
            Boolean match1 = user.get().getSecurityAnswer().equalsIgnoreCase(answer1);
            if (match1){
                return ResponseEntity.ok().body(Map.of("message", "security questions correct", "userID", userID));
            }else{
                return ResponseEntity.badRequest().body(Map.of("error", "security questions not correct"));
            }
        }else{
            return ResponseEntity.badRequest().body(Map.of("error", "Could not find ID or email"));
        }   
    }


    public ResponseEntity<?> setNewPassword(Map<String,String> newPasswordData, String userID){
        String newPassword = newPasswordData.get("newPassword");
        newPassword = PasswordGenerator.hashPassword(newPassword);

        if (newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Password cannot be empty"));
        }

        Optional<Users> userOptional = userRepository.findByUserID(userID);

        if(userOptional.isPresent()){
            Users user = userOptional.get();
            Optional<Passwords> passwordOptional = passwordRepository.findById(user.getPassRef());
            
            if(passwordOptional.isPresent()){
                Passwords password = passwordOptional.get();

                password.addOldPasswords(password.getPasswordHash());
                password.setPasswordHash(newPassword);
                passwordRepository.save(password);
            }else{
                return ResponseEntity.badRequest().body(Map.of("error", "Password record not found"));
            }
            return ResponseEntity.ok().body(Map.of("message", "Password updated successfully"));

        }else{
            return ResponseEntity.badRequest().body(Map.of("error", "User not found"));
        }
    }

    public ResponseEntity<?> createRequestedUser(Map<String,String> accountData){
        String FName = accountData.get("firstName"), LName = accountData.get("lastName");
        String address = accountData.get("address"), email = accountData.get("email");
        String DOBString = accountData.get("DOB");

        String userNameID = generateRecID(FName, LName, DOBString);

        Optional<Users> emailDuplicate = userRepository.findByEmail(email);
        Optional<RequestedUser> emailDuplicate2 = requestedUserRepository.findByEmail(email);
        if (emailDuplicate.isPresent() || emailDuplicate2.isPresent())
            return ResponseEntity.badRequest().body(Map.of("error", "email already registered"));

        if (userNameID.equals("Invalid"))
            return ResponseEntity.badRequest().body(Map.of("error", "userNameId didn't not generate successfully"));
 
        Date DOB;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DOB = dateFormat.parse(DOBString);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid DOB format. Use YYYY-MM-DD"));
        }
        try{
        RequestedUser newReqUser = new RequestedUser();
        setUserReq(newReqUser, userNameID, FName, LName, address, email, DOB);
        requestedUserRepository.save(newReqUser);
        return ResponseEntity.ok().body(Map.of(
                    "message", "Account created successfully",
                    "userID", newReqUser.getUserName()
                ));
        } catch( Exception e){
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }

    }



    public ResponseEntity<?> createAccount(Map<String,String> accountData, String makerID){
        String FName = accountData.get("firstName"), LName = accountData.get("lastName");
        String address = accountData.get("address"), email = accountData.get("email");
        String DOBString = accountData.get("DOB"), AccountType = accountData.get("userType");
        
        System.out.println("Data: " + FName +"|"+LName+"|"+address+"|"+email+"|"+DOBString+"|"+AccountType+"|"+makerID);
        
        Optional<Users> userOptional = userRepository.findByUserID(makerID);
        Optional<Users> emailDuplicate = userRepository.findByEmail(email);
        Optional<RequestedUser> emailDuplicate2 = requestedUserRepository.findByEmail(email);
        if (emailDuplicate.isPresent() || emailDuplicate2.isPresent())
            return ResponseEntity.badRequest().body(Map.of("error", "email already in requests or already registered"));


        if(userOptional.isPresent()){
            Users user = userOptional.get();
            if(user.getUserType().equals("Admin") && user.getActive()){
                String userNameID = generateAccountID(FName, LName, DOBString);
                System.out.print(userNameID);
                    if (userNameID.equals("Invalid"))
                        return ResponseEntity.badRequest().body(Map.of("error", "userNameId didn't not generate successfully"));

                String uhashedpasswordHash = PasswordGenerator.generateRandomPassword();
                if (uhashedpasswordHash == null || uhashedpasswordHash.isEmpty()) {
                    return ResponseEntity.badRequest().body(Map.of("error", "Generated password is invalid"));
                }
                //Create a password row
                System.out.println("Unhashed:" +uhashedpasswordHash);

                String passwordHash = PasswordGenerator.hashPassword(uhashedpasswordHash);

                Passwords passwordRec = new Passwords(passwordHash, "");
                passwordRec.setOldPasswords("");
                System.out.println("PasswordHash:" +passwordHash);
                System.out.println(passwordRec.getPasswordHash() + " : " + passwordRec.getOldPasswords() + " : " + passwordRec.getPasswordRef());
                passwordRepository.save(passwordRec);
                Date DOB;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    DOB = dateFormat.parse(DOBString);
                } catch (ParseException e) {
                    return ResponseEntity.badRequest().body(Map.of("error", "Invalid DOB format. Use YYYY-MM-DD"));
                }

                //Add user account with the password reference
                Users userRec = new Users();
                setUser(userRec, userNameID, FName, LName,address,email, AccountType, DOB);
                userRec.setPassRef(passwordRec.getPasswordRef());
                userRepository.save(userRec);
                
                // Send email with generated password
                emailService.sendEmail(email, "Your New Account Password",
                        "Hello " + userNameID + ",\n\nYour generated password is: " + uhashedpasswordHash +
                        "\n\nPlease log in and change your password immediately.");

                return ResponseEntity.ok().body(Map.of(
                    "message", "Account created successfully",
                    "userID", userRec.getUserName(),
                    "generatedPassword", passwordHash
                ));
            }else{
                return ResponseEntity.badRequest().body(Map.of("error", "Account isn't an admin"));
            }
        }else{
            return ResponseEntity.badRequest().body(Map.of("error", "Maker not found: " + makerID));
        }
    }


        public ResponseEntity<?> acceptCreateAccount(Map<String,String> accountData, String makerID){
        String FName = accountData.get("firstName"), LName = accountData.get("lastName");
        String address = accountData.get("address"), email = accountData.get("email");
        String DOBString = accountData.get("DOB"), AccountType = accountData.get("userType");
        
        System.out.println("Data: " + FName +"|"+LName+"|"+address+"|"+email+"|"+DOBString+"|"+AccountType+"|"+makerID);
        
        Optional<Users> userOptional = userRepository.findByUserID(makerID);
        Optional<Users> emailDuplicate = userRepository.findByEmail(email);
        if (emailDuplicate.isPresent() )
            return ResponseEntity.badRequest().body(Map.of("error", "email already in requests or already registered"));


        if(userOptional.isPresent()){
            Users user = userOptional.get();
            if(user.getUserType().equals("Admin") && user.getActive()){
                String userNameID = generateAccountID(FName, LName, DOBString);
                System.out.print(userNameID);
                    if (userNameID.equals("Invalid"))
                        return ResponseEntity.badRequest().body(Map.of("error", "userNameId didn't not generate successfully"));

                String passwordHash = PasswordGenerator.generateRandomPassword();
                if (passwordHash == null || passwordHash.isEmpty()) {
                    return ResponseEntity.badRequest().body(Map.of("error", "Generated password is invalid"));
                }
                
                //Create a password row
                passwordHash = PasswordGenerator.hashPassword(passwordHash);

                Passwords passwordRec = new Passwords(passwordHash, "");
                passwordRec.setOldPasswords("");
                System.out.println("PasswordHash:" +passwordHash);
                System.out.println(passwordRec.getPasswordHash() + " : " + passwordRec.getOldPasswords() + " : " + passwordRec.getPasswordRef());
                passwordRepository.save(passwordRec);
                Date DOB;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    DOB = dateFormat.parse(DOBString);
                } catch (ParseException e) {
                    return ResponseEntity.badRequest().body(Map.of("error", "Invalid DOB format. Use YYYY-MM-DD"));
                }

                //Add user account with the password reference
                Users userRec = new Users();
                setUser(userRec, userNameID, FName, LName,address,email, AccountType, DOB);
                userRec.setPassRef(passwordRec.getPasswordRef());
                userRepository.save(userRec);
                
                // Send email with generated password
                emailService.sendEmail(email, "Your New Account Password",
                        "Hello " + userNameID + ",\n\nYour generated password is: " + passwordHash +
                        "\n\nPlease log in and change your password immediately.");

                return ResponseEntity.ok().body(Map.of(
                    "message", "Account created successfully",
                    "userID", userRec.getUserName(),
                    "generatedPassword", passwordHash
                ));
            }else{
                return ResponseEntity.badRequest().body(Map.of("error", "Account isn't an admin"));
            }
        }else{
            return ResponseEntity.badRequest().body(Map.of("error", "Maker not found: " + makerID));
        }
    }



    public ResponseEntity<?> acceptRequest(Map<String, String> accountData, String makerID) {
        String userNameID = accountData.get("userID");
        Optional<Users> maker = userRepository.findById(makerID);

        if (maker.isEmpty() || !maker.get().getUserType().equals("Admin")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Maker is not an admin or does not exist"));
        }

        Optional<RequestedUser> reqUserRec = requestedUserRepository.findById(userNameID);
        if (reqUserRec.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Requested User Not Found: " + userNameID));
        }

        RequestedUser reqUser = reqUserRec.get();

        // **Wait for createAccount to finish and check the result**
        ResponseEntity<?> creationResponse = acceptCreateAccount(accountData, makerID);

        // **Only delete the request if the account creation was successful**
        if (creationResponse.getStatusCode().is2xxSuccessful()) {
            requestedUserRepository.delete(reqUser);
            return ResponseEntity.ok().body(Map.of("message", "User request accepted and removed: " + userNameID));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Account creation failed", "details", creationResponse.getBody()));
        }
    }


    public ResponseEntity<?> denyRequest(Map<String,String> accountData, String makerID){
        String userNameID = accountData.get("userID");
        Optional<Users> maker = userRepository.findById(makerID);

        if(maker.isEmpty() || !maker.get().getUserType().equals("Admin")){
            return ResponseEntity.badRequest().body(Map.of("error", "Maker is not an admin or does not exist"));
        }

        Optional<RequestedUser> reqUser = requestedUserRepository.findById(userNameID);
        if(reqUser.isPresent()){
            requestedUserRepository.delete(reqUser.get());
            return ResponseEntity.ok().body(Map.of("message", "User request denied and removed: " + userNameID));
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Requested User Not Found: " + userNameID));
    }


    public ResponseEntity<?> editUserRequest(Map<String,String> userData, String makerID) {
        String userNameID = userData.get("userID");
        String FName = userData.get("firstName"), LName = userData.get("lastName");
        String address = userData.get("address"), email = userData.get("email");
        String DOBString = userData.get("DOB"), AccountType = userData.get("userType");

        Optional<Users> maker = userRepository.findById(makerID);
        if (maker.isEmpty() || !maker.get().getUserType().equals("Admin")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Not an admin or maker not found"));
        }
        
        if (!maker.get().getActive()){
            return ResponseEntity.badRequest().body(Map.of("error", "Maker is not active"));
        }
        Optional<Users> userOptional = userRepository.findById(userNameID);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Requested User Not Found: " + userNameID));
        }

        Users userRec = userOptional.get();

        //Lambda-style updates using `if` condition
        Optional.ofNullable(FName).filter(fn -> !fn.equals(userRec.getFirstName())).ifPresent(userRec::setFirstName);
        Optional.ofNullable(LName).filter(ln -> !ln.equals(userRec.getLastName())).ifPresent(userRec::setLastName);
        Optional.ofNullable(address).filter(ad -> !ad.equals(userRec.getAddress())).ifPresent(userRec::setAddress);
        Optional.ofNullable(email).filter(em -> !em.equals(userRec.getEmail())).ifPresent(userRec::setEmail);
        Optional.ofNullable(DOBString).ifPresent(dob -> {
            try {
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                if (!DOB.equals(userRec.getDOB())) {
                    userRec.setDOB(DOB);
                }
            } catch (ParseException e) {
                throw new RuntimeException("Invalid DOB format. Use YYYY-MM-DD");
            }
        });
        Optional.ofNullable(AccountType).filter(ac -> !ac.equals(userRec.getUserType())).ifPresent(userRec::setUserType);

        userRepository.save(userRec); // Save updated user
        return ResponseEntity.ok(Map.of("message", "User updated successfully", "userID", userNameID));
    }

    public ResponseEntity<?> changeUserStatus(Map<String, String> userData, String makerID) {
        String userNameID = userData.get("userID");
        String statusString = userData.get("activeStatus");

        if (statusString == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing activeStatus field"));
        }

        Boolean currentStatus = Boolean.parseBoolean(statusString) || statusString.equalsIgnoreCase("Active");

        Optional<Users> maker = userRepository.findById(makerID);
        if (maker.isEmpty() || !maker.get().getUserType().equals("Admin")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Not an admin or maker not found"));
        }

        Optional<Users> userOptional = userRepository.findById(userNameID);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Requested User Not Found: " + userNameID));
        }

        Users userRec = userOptional.get();
        if (!userRec.getActive().equals(currentStatus)) {
            userRec.setActive(currentStatus);
            userRepository.save(userRec);
            System.out.println("User status updated: " + userNameID + " -> " + currentStatus);
        } else {
            System.out.println("User status unchanged: " + userNameID);
        }

        return ResponseEntity.ok(Map.of(
            "message", "User status updated successfully",
            "userID", userNameID,
            "status", userRec.getActive() ? "Active" : "Inactive"
        ));
    }



    public String generateAccountID(String FName, String LName, String DOB){
        String newUserName = "";
        int addTo = 1;
        Boolean repeat = true;
        String[] birthdate = DOB.split("-");
        if(birthdate.length != 3){
            return "Invalid";
        }
        while(repeat){
            //First Initial + LastName + Month + Year
            if (addTo == 1)
                newUserName = FName.charAt(0)+ LName + birthdate[1] + birthdate[0].substring(2,4);
            else
                newUserName = FName.charAt(0)+ LName + birthdate[1] + birthdate[0].substring(2,4) + addTo;

            Optional<Users> userOptional = userRepository.findByUserID(newUserName);
            if(!userOptional.isPresent())
                repeat = false;
            else
                addTo+=1;
                
            
        }
        return newUserName;

    }
    public String generateRecID(String FName, String LName, String DOB){
        String newUserName = "";
        int addTo = 1;
        Boolean repeat = true;
        String[] birthdate = DOB.split("-");
                if(birthdate.length != 3){
            return "Invalid";
        }

        while(repeat){
            //First Initial + LastName + Month + Year
            if (addTo == 1)
                newUserName = FName.charAt(0)+ LName + birthdate[1] + birthdate[0].substring(2,4);
            else
                newUserName = FName.charAt(0)+ LName + birthdate[1] + birthdate[0].substring(2,4) + addTo;

            Optional<RequestedUser> userReqOptional = requestedUserRepository.findById(newUserName);
            if(!userReqOptional.isPresent())
                repeat = false;
            else
                addTo+=1;
                
            
        }
        return newUserName;

    }



    public void setUser(Users user, String userName, String FName, String LName, String address, String email, String userType, Date DOB){
        user.setActive(true);
        user.setUserName(userName);
        user.setFirstName(FName);
        user.setLastName(LName);
        user.setAddress(address);
        user.setEmail(email);
        user.setDOB(DOB);
        user.setUserType(userType);
    }

        public void setUserReq(RequestedUser reqUser, String userName, String FName, String LName, String address, String email, Date DOB){
        reqUser.setUserName(userName);
        reqUser.setFirstName(FName);
        reqUser.setLastName(LName);
        reqUser.setAddress(address);
        reqUser.setEmail(email);
        reqUser.setDOB(DOB);
    }



}
