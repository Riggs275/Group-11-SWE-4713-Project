import * as React from "react";
import { useTable, useSortBy } from "react-table";
import { useNavigate, useLocation, Link } from "react-router-dom";
import { changeUserStatusRequest, acceptAccountRequest, denyAccountRequest  } from "./api";
import { CreateNewUser } from "./Buttons";
export function UserTable({ userData }) {
    // Store user data in state and ensure 'activeStatus' is computed properly
    const [users, setUsers] = React.useState(() =>
        userData.map(user => ({
            ...user,
            activeStatus: user.active ? "Active" : "Inactive"
        }))
    );

    // Ensure the table updates if 'userData' changes (e.g., after fetching new data)
    React.useEffect(() => {
        setUsers(userData.map(user => ({
            ...user,
            activeStatus: user.active ? "Active" : "Inactive"
        })));
    }, [userData]);

    const location = useLocation();
    const navigate = useNavigate();
    const makerID = location.state?.userID || localStorage.getItem("userID");

    const handleDeactivate = async (userName, isActive) => {
        const payload = {
            makerID: makerID, 
            userID: userName,  
            activeStatus: isActive ? "Inactive" : "Active"
        };

        console.log("Deactivating user:", payload);

        try {
            const response = await changeUserStatusRequest(payload);
            if (response.success) {
                console.log("User status changed successfully");

                // **Refresh the table by updating state**
                setUsers(prevUsers =>
                    prevUsers.map(user =>
                        user.userName === userName
                            ? { ...user, active: !isActive, activeStatus: isActive ? "Inactive" : "Active" }
                            : user
                    )
                );
            } else {
                console.error("Failed to change user status");
            }
        } catch (error) {
            console.error("Error changing user status:", error);
        }
    };

    const columns = React.useMemo(() => [
        { Header: "User ID", accessor: "userName" },
        { Header: "First Name", accessor: "firstName" },
        { Header: "Last Name", accessor: "lastName" },
        { Header: "Date of Birth", accessor: "dob" },
        { Header: "Email", accessor: "email" },
        { Header: "User Type", accessor: "userType" },
        { Header: "Status", accessor: "activeStatus" },  // ✅ Status is correctly mapped
        {
            Header: "Modify", 
            accessor: "modifying", 
            disableSortBy: true,
            Cell: ({ row }) => (
                <div style={{ display: "flex", gap: "10px" }}>
                    <button 
                        onClick={() => navigate("/modifyAccountAdmin", { state: row.original })}
                        style={{ background: "#4681f4", color: "white", border: "none", padding: "5px 10px", cursor: "pointer" }}
                    >
                        Edit
                    </button>
                    <button 
                        onClick={() => handleDeactivate(row.original.userName, row.original.active)}
                        style={{ background: row.original.active ? "red" : "green", color: "white", border: "none", padding: "5px 10px", cursor: "pointer" }}
                    >
                        {row.original.active ? "Deactivate" : "Activate"}
                    </button>
                </div>
            )
        }
    ], []);

    const tableInstance = useTable({ columns, data: users }, useSortBy);
    const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } = tableInstance;

    return (
        <div className="userTable">
            <Link to="/addAccountAdmin">
                <CreateNewUser />
            </Link>
            <table {...getTableProps()}>
                <thead>
                    {headerGroups.map(headerGroup => (
                        <tr {...headerGroup.getHeaderGroupProps()}>
                            {headerGroup.headers.map(column => (
                                <th 
                                    {...column.getHeaderProps(column.getSortByToggleProps())}
                                    style={{ cursor: "pointer" }}
                                >
                                    {column.render("Header")}
                                    <span>
                                        {column.isSorted
                                            ? column.isSortedDesc
                                                ? " 🔽" // Descending
                                                : " 🔼" // Ascending
                                            : " ---"}
                                    </span>
                                </th>
                            ))}
                        </tr>
                    ))}
                </thead>
                <tbody {...getTableBodyProps()}>
                    {rows.map(row => {
                        prepareRow(row);
                        return (
                            <tr {...row.getRowProps()}>
                                {row.cells.map(cell => (
                                    <td {...cell.getCellProps()}>
                                        {cell.render("Cell")}
                                    </td>
                                ))}
                            </tr>
                        );
                    })}
                </tbody>
            </table>
        </div>
    );
}

export function RequestTable({ userData }) {
    const [users, setUsers] = React.useState(userData);
    const [showModal, setShowModal] = React.useState(false);
    const [selectedUser, setSelectedUser] = React.useState(null);
    const [userType, setUserType] = React.useState("Admin"); // Default selection
    const location = useLocation();
    const makerID = location.state?.userID || localStorage.getItem("userID") || "default_maker_id";

    // Ensure state updates if 'userData' changes
    React.useEffect(() => {
        setUsers(userData);
    }, [userData]);

    const handleAcceptClick = (user) => {
        setSelectedUser(user);
        setShowModal(true);
    };

    const handleAcceptSubmit = async () => {
        if (!selectedUser) return;

        const payload = {
            makerID: makerID, // Ensure makerID is included
            userID: selectedUser.userName,
            firstName: selectedUser.firstName,
            lastName: selectedUser.lastName,
            address: selectedUser.address,
            email: selectedUser.email,
            DOB: selectedUser.dob, 
            userType: userType, 
        };

        console.log("Accepting user with role:", payload);

        try {
            const response = await acceptAccountRequest(payload);
            if (response.success) {
                console.log("User accepted successfully");

                // Remove accepted user from table **only if API call succeeds**
                setUsers(prevUsers => prevUsers.filter(u => u.userName !== selectedUser.userName));
            } else {
                console.error("Failed to accept user:", response.error);
            }
        } catch (error) {
            console.error("Error accepting user:", error);
        }

        setShowModal(false);
        setSelectedUser(null);
    };

    const handleDeny = async (user) => {
        const payload = {
            makerID: makerID, // Ensure makerID is included
            userID: user.userName,
            firstName: user.firstName,
            lastName: user.lastName,
            address: user.address,
            email: user.email,
            DOB: user.dob, 
            userType: user.userType, 
        };

        console.log("Denying user:", payload);

        try {
            const response = await denyAccountRequest(payload);
            if (response.success) {
                console.log("User denied successfully");

                // Remove denied user from table **only if API call succeeds**
                setUsers(prevUsers => prevUsers.filter(u => u.userName !== user.userName));
            } else {
                console.error("Failed to deny user:", response.error);
            }
        } catch (error) {
            console.error("Error denying user:", error);
        }
    };

    const columns = React.useMemo(() => [
        { Header: "User ID", accessor: "userName" },
        { Header: "First Name", accessor: "firstName" },
        { Header: "Last Name", accessor: "lastName" },
        { Header: "Date of Birth", accessor: "dob" },
        { Header: "Email", accessor: "email" },
        { Header: "Address", accessor: "address" },
        {
            Header: "Actions",
            accessor: "actions",
            disableSortBy: true,
            Cell: ({ row }) => (
                <div style={{ display: "flex", gap: "10px" }}>
                    <button 
                        onClick={() => handleAcceptClick(row.original)}
                        style={{ background: "green", color: "white", border: "none", padding: "5px 10px", cursor: "pointer" }}
                    >
                        Accept
                    </button>
                    <button 
                        onClick={() => handleDeny(row.original)}
                        style={{ background: "red", color: "white", border: "none", padding: "5px 10px", cursor: "pointer" }}
                    >
                        Deny
                    </button>
                </div>
            )
        }
    ], []);

    const tableInstance = useTable({ columns, data: users }, useSortBy);
    const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } = tableInstance;

    return (
        <div className="userTable">
            <table {...getTableProps()}>
                <thead>
                    {headerGroups.map(headerGroup => (
                        <tr {...headerGroup.getHeaderGroupProps()}>
                            {headerGroup.headers.map(column => (
                                <th 
                                    {...column.getHeaderProps(column.getSortByToggleProps())}
                                    style={{ cursor: "pointer" }}
                                >
                                    {column.render("Header")}
                                    <span>
                                        {column.isSorted
                                            ? column.isSortedDesc
                                                ? " 🔽"
                                                : " 🔼"
                                            : " --- "}
                                    </span>
                                </th>
                            ))}
                        </tr>
                    ))}
                </thead>
                <tbody {...getTableBodyProps()}>
                    {rows.map(row => {
                        prepareRow(row);
                        return (
                            <tr {...row.getRowProps()}>
                                {row.cells.map(cell => (
                                    <td {...cell.getCellProps()}>
                                        {cell.render("Cell")}
                                    </td>
                                ))}
                            </tr>
                        );
                    })}
                </tbody>
            </table>

            {/* Popup Modal */}
            {showModal && selectedUser && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Select User Type for {selectedUser.firstName} {selectedUser.lastName}</h2>
                        <label>
                            User Type:
                            <select value={userType} onChange={(e) => setUserType(e.target.value)}>
                                <option value="Admin">Admin</option>
                                <option value="Accountant">Accountant</option>
                                <option value="Manager">Manager</option>
                            </select>
                        </label>
                        <div style={{ marginTop: "10px" }}>
                            <button onClick={handleAcceptSubmit} style={{ marginRight: "10px", padding: "5px 10px" }}>Confirm</button>
                            <button onClick={() => setShowModal(false)} style={{ padding: "5px 10px", backgroundColor: "gray", color: "white" }}>Cancel</button>
                        </div>
                    </div>
                </div>
            )}

            {/* Modal CSS */}
            <style>
                {`
                .modal {
                    position: fixed;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    background: rgba(0, 0, 0, 0.5);
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }
                .modal-content {
                    background: white;
                    padding: 20px;
                    border-radius: 5px;
                    width: 300px;
                    text-align: center;
                }
                `}
            </style>
        </div>
    );
}
