const API_BASE_URL = "http://localhost:8080";


export const loginUserRequest = async(formData) =>{
    try{
        const response = await fetch(`${API_BASE_URL}/login/loginUserRequest`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(formData),
        });
        if(!response.ok) throw new Error("Failed to find user name or password didn't match");
        console.log("We found your account sending you to the correct page")
        return { success: true };
    } catch (error){
        console.error("Error", error)
        return { success: false, errMessage: error };
    }
}

export const forgotAccountRequest = async (formData) => {
    try {
        const response = await fetch(`${API_BASE_URL}/forgotAccountRequest`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData),
        });
        if (!response.ok) throw new Error("Failed to find account");
        console.log("We sent a request successfully");
        return { success: true };
    } catch (error) {
        console.error("Error:", error);
        return { success: false, errMessage: error };
    }
};


export const createAccountRequest = async (formData) => {
    try {
        const response = await fetch(`${API_BASE_URL}/createAccountRequest`,{
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(formData),
        });
        if (!response.ok) throw new Error("Failed to send the request to admin");
        console.log("We successfully sent the request to the admin");
        return { success: true, response: response, successMessage: "User ID created sucessfully"};

    } catch(error) { 
        console.error("Error:",error);
        return { success: false, errMessage: error };

    }
}

export const setNewPassword=async (formData) =>{
    try{
        const response = await fetch(`${API_BASE_URL}/setNewPassword`,{
            method: "POST",
            headers: {"Content-Type" : "application/json"},
            body: JSON.stringify(formData),
        });
        if(!response.ok) throw new Error("Failed to set new password for this account");
        console.log("we have successfully changed the password for this account");
        return { success: true, response: response, successMessage: "New password set sucessfully"};

    } catch(error){
        console.error("Error", error);
        return { success: false, errMessage: error };
    }
}

export const checkSecurityQ=async (formData)=>{
    try{
        const response = await fetch(`${API_BASE_URL}/checkSecurityQ`,{
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(formData),
        });
        if(!response.ok) throw new Error("Security Questions were not correct or something else happened");
        return { success: true };

    } catch(error){
        console.error("Error", error);
        return { success: false, errMessage: error };
    }
}



