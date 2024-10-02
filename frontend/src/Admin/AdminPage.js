// src/AdminPage.js
import React from 'react';
import styles from './AdminPage.module.css'; // Assuming you have a CSS module for styling
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUserShield, faUserCog, faSignOutAlt } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';

const AdminPage = () => {
    const navigate = useNavigate();

    const handleManageUsers = () => {
        navigate('/manage-users'); // Navigate to User Management Page
    };

    const handleViewReports = () => {
        navigate('/view-reports'); // Navigate to Reports Page
    };

    const handleLogout = () => {
        console.log('Logging out...');
        // Here, you can implement any logout logic if needed (e.g., clearing user data)
        
        // Navigate back to the login page
        navigate('/login');
    };

    return (
        <div className={styles.container}>
            <div className={styles.profile}>
                <div className={styles.avatar}>
                    <FontAwesomeIcon icon={faUserShield} size="6x" />
                </div>
                <div className={styles.details}>
                    <h1>Welcome, Admin!</h1>
                    <p>Manage users and view reports from here.</p>
                </div>
            </div>
            <div className={styles.actions}>
                <button className={styles.actionBtn} onClick={handleManageUsers}>
                    <FontAwesomeIcon icon={faUserCog} size="lg" />
                    Manage Users
                </button>
                <button className={styles.actionBtn} onClick={handleViewReports}>
                    <FontAwesomeIcon icon={faUserCog} size="lg" />
                    View Reports
                </button>
                <button className={styles.actionBtn} onClick={handleLogout}>
                    <FontAwesomeIcon icon={faSignOutAlt} size="lg" />
                    Logout
                </button>
            </div>
        </div>
    );
};

export default AdminPage;
