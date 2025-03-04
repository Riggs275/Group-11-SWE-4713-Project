import * as React from "react";
import { useTable, useSortBy } from "react-table";
import { CreateNewUser } from "./Buttons";
import { Link } from 'react-router-dom';

export function UserTable({ userData }) {
    const data = React.useMemo(() => userData, [userData]);

    const columns = React.useMemo(() => [
        { Header: "User ID", accessor: "UserNameID" },
        { Header: "First Name", accessor: "FirstName" },
        { Header: "Last Name", accessor: "LastName" },
        { Header: "Date of Birth", accessor: "DOB" },
        { Header: "User Type", accessor: "UserType" },
        { Header: "Status", accessor: "ActiveStatus" }
    ], []);

    const tableInstance = useTable(
        { columns, data }, 
        useSortBy // ✅ Ensure it's always included here
    );

    const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } = tableInstance;

    return (
        <div className="userTable">
            <Link to="/addAccountAdmin">
                <CreateNewUser/>
            </Link>
            <table {...getTableProps()}>
                <thead>
                    {headerGroups.map((headerGroup) => (
                        <tr {...headerGroup.getHeaderGroupProps()}>
                            {headerGroup.headers.map((column) => (
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
                    {rows.map((row) => {
                        prepareRow(row);
                        return (
                            <tr {...row.getRowProps()}>
                                {row.cells.map((cell) => (
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
        { Header: "User ID", accessor: "UserNameID" },
        { Header: "First Name", accessor: "FirstName" },
        { Header: "Last Name", accessor: "LastName" },
        { Header: "Date of Birth", accessor: "DOB" },
        { Header: "Address", accessor: "Address" },
        {
            Header: "Actions",
            accessor: "actions",
            disableSortBy: true, // Disable sorting for this column
            Cell: ({ row }) => (
                <div style={{ display: "flex", gap: "10px" }}>
                    <button 
                        onClick={() => console.log("Accept")} //onDelete(row.original.UserNameID)
                        style={{ background: "#4681f4", color: "white", border: "none", padding: "5px 10px", cursor: "pointer" }}
                    >
                        Accept
                    </button>
                    <button 
                        onClick={() => console.log("Refuse")} 
                        style={{ background: "red", color: "white", border: "none", padding: "5px 10px", cursor: "pointer" }}
                    >
                        Deny
                    </button>
                </div>
            )
        }
    ], []);

    const tableInstance = useTable(
        { columns, data }, 
        useSortBy // ✅ Ensure it's always included here
    );

    const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } = tableInstance;

    return (
        
        <div className="userTable">
            <table {...getTableProps()}>
                <thead>
                    {headerGroups.map((headerGroup) => (
                        <tr {...headerGroup.getHeaderGroupProps()}>
                            {headerGroup.headers.map((column) => (
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
                    {rows.map((row) => {
                        prepareRow(row);
                        return (
                            <tr {...row.getRowProps()}>
                                {row.cells.map((cell) => (
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
