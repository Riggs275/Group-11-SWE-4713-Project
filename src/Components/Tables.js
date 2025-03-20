import * as React from "react";
import { useTable, useSortBy } from "react-table";
import { useNavigate, Link } from "react-router-dom";
import { CreateNewUser } from "./Buttons";

export function UserTable({ userData }) {
    const data = React.useMemo(() => userData.map(user => ({
        ...user,
        activeStatus: user.activeStatus ? "Active" : "Inactive" // Convert boolean to readable text
    })), [userData]);

    const navigate = useNavigate();
    
    const columns = React.useMemo(() => [
        { Header: "User ID", accessor: "userNameID" },
        { Header: "First Name", accessor: "firstName" },
        { Header: "Last Name", accessor: "lastName" },
        { Header: "Date of Birth", accessor: "dob" },
        { Header: "User Type", accessor: "userType" },
        { Header: "Status", accessor: "activeStatus" },
        {
            Header: "Modify", accessor: "modifying", disableSortBy: true,
            Cell: ({ row }) => (
                <div style={{ display: "flex", gap: "10px" }}>
                    <button 
                        onClick={() => navigate("/modifyAccountAdmin", { state: row.original }) }
                        style={{ background: "#4681f4", color: "white", border: "none", padding: "5px 10px", cursor: "pointer" }}
                    >
                        Edit
                    </button>
                    <button 
                        onClick={() => console.log("Deactivating User:", row.original.userNameID) }
                        style={{ background: "red", color: "white", border: "none", padding: "5px 10px", cursor: "pointer" }}
                    >
                        Deactivate
                    </button>
                </div>
            )
        }
    ], []);

    const tableInstance = useTable({ columns, data }, useSortBy);
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
    const data = React.useMemo(() => userData, [userData]);

    const columns = React.useMemo(() => [
        { Header: "User ID", accessor: "userNameID" },
        { Header: "First Name", accessor: "firstName" },
        { Header: "Last Name", accessor: "lastName" },
        { Header: "Date of Birth", accessor: "dob" },
        { Header: "Address", accessor: "address" },
        {
            Header: "Actions",
            accessor: "actions",
            disableSortBy: true, // Disable sorting for this column
            Cell: ({ row }) => (
                <div style={{ display: "flex", gap: "10px" }}>
                    <button 
                        onClick={() => console.log("Accepting User:", row.original.userNameID) }
                        style={{ background: "#4681f4", color: "white", border: "none", padding: "5px 10px", cursor: "pointer" }}
                    >
                        Accept
                    </button>
                    <button 
                        onClick={() => console.log("Denying User:", row.original.userNameID) }
                        style={{ background: "red", color: "white", border: "none", padding: "5px 10px", cursor: "pointer" }}
                    >
                        Deny
                    </button>
                </div>
            )
        }
    ], []);

    const tableInstance = useTable({ columns, data }, useSortBy);
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
                                                ? " 🔽" // Descending
                                                : " 🔼" // Ascending
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
        </div>
    );
}
