const API_BASE_URL = "http://localhost:8080";

const apiRequest = async (endpoint, formData, errorMessage) => {
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData),
        });
        if (!response.ok) throw new Error(errorMessage);
        const data = await response.json();
        return { success: true, data };
    } catch (error) {
        console.error("Error:", error);
        return { success: false, errMessage: error.message };
    }
};

// LOGIN USER
export const loginUserRequest = (formData) => apiRequest(
    "/loginUserRequest",
    formData,
    "Failed to find user name or password didn't match"
);

// FORGOT ACCOUNT REQUEST
export const forgotAccountRequest = (formData) => apiRequest(
    "/forgotAccountRequest",
    formData,
    "Failed to find account"
);

// CREATE ACCOUNT REQUEST (Sends Request to Admin)
export const createAccountRequest = (formData) => apiRequest(
    "/createAccountRequest",
    formData,
    "Failed to send the request to admin"
);

//ADMIN ACCEPTS REQUESTED USER AND CONVERTS TO FULL ACCOUNT
export const acceptAccountRequest = (formData) => apiRequest(
    "/acceptRequest",
    formData,
    "Failed to accept request"
);

//ADMIN DENIES REQUEST
export const denyAccountRequest = (formData) => apiRequest(
    "/denyRequest",
    formData,
    "Failed to deny request"
);

// ADMIN EDITS USER DETAILS
export const editUserRequest = (formData) => apiRequest(
    "/editUser",
    formData,
    "Failed to edit user"
);

//ADMIN CHANGES USER ACTIVE STATUS (Activate/Deactivate)
export const changeUserStatusRequest = (formData) => apiRequest(
    "/changeUserStatus",
    formData,
    "Failed to change user status"
);
// ADMIN ADD ACCOUNT (Direct Creation by Admin)
export const adminAddAccountRequest = (formData) => apiRequest(
    "/createAccount",
    formData,
    "Failed to add account"
);

// SET NEW PASSWORD
export const setNewPassword = (formData) => apiRequest(
    "/setNewPassword",
    formData,
    "Failed to set new password for this account"
);



// CHECK SECURITY QUESTIONS
export const checkSecurityQ = (formData) => apiRequest(
    "/checkSecurityQ",
    formData,
    "Security Questions were not correct or something else happened"
);

// GET ALL TABLES 
export const getUsersTable = (formData) => apiRequest(
    "/getAllUsers",
    formData,
    "Couldn't get the Users"
);

// GET ALL TABLES 
export const getRequestedUsersTable = (formData) => apiRequest(
    "/getAllRequestedUsers",
    formData,
    "Couldn't get the requested users"
);

// FETCH SINGLE ACCOUNT BY ID
export async function fetchAccountById(id) {
    return apiRequest(
        "/fetchAccountById",
        { id },
        "Could not fetch account"
    );
}

// FETCH EVENT LOG FOR ACCOUNT
export async function fetchEventLog(accountId) {
    return apiRequest(
        "/fetchEventLog",
        { accountId },
        "Could not fetch event log"
    );
}

// UPDATE ACCOUNT
export async function updateAccount(data) {
    return apiRequest(
        "/updateAccount",
        data,
        "Could not update account"
    );
}

// CHANGE USER PASSWORD
export async function changePassword(userID, newPassword) {
    return apiRequest(
        "/changePassword",
        { userID, newPassword },
        "Could not change password"
    );
}

// FETCH ALL ACCOUNTS (Chart of Accounts)
export async function fetchAccounts() {
    return apiRequest(
        "/getAccounts",
        {},
        "Could not fetch accounts"
    );
}

// GENERATE FINANCIAL REPORT (Dynamic Report Type)
export async function generateReport(params) {
    return apiRequest(
        "/generateReport",
        params,
        "Could not generate report"
    );
}

// FETCH LEDGER ENTRIES FOR AN ACCOUNT
export async function fetchLedgerEntries(accountId) {
    return apiRequest(
        "/getLedgerEntries",
        { accountId },
        "Could not fetch ledger entries"
    );
}

// FETCH USER PROFILE
export async function fetchUserProfile(userID) {
    return apiRequest(
        "/getUserProfile",
        { userID },
        "Could not fetch user profile"
    );
}

// UPDATE USER PROFILE
export async function updateUserProfile(profileData) {
    return apiRequest(
        "/updateUserProfile",
        profileData,
        "Could not update user profile"
    );
}
