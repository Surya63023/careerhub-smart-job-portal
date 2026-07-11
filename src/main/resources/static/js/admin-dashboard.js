// ==========================================================
// CAREER HUB - ADMIN DASHBOARD
// admin-dashboard.js
// ==========================================================

// ==========================================================
// API CONFIG
// ==========================================================

const API_BASE_URL = "";

// ==========================================================
// AUTH
// ==========================================================

const token =
    localStorage.getItem("token");

if (!token) {

    window.location.href =
        "/html/login.html";
}

// ==========================================================
// GLOBAL STATE
// ==========================================================

let adminUser = null;

let dashboardStats = null;

let recruiters = [];

let companies = [];

// ==========================================================
// DOM READY
// ==========================================================

document.addEventListener(
    "DOMContentLoaded",
    initializeDashboard
);

// ==========================================================
// INITIALIZE DASHBOARD
// ==========================================================

async function initializeDashboard() {

    initializeSidebar();

    initializeTheme();

    initializeLogout();

    try {
		
		await loadAdminProfile();

		   await loadDashboardStats();

		   await loadUsers();

		   await loadCandidates();

		   await loadRecruiters();

		   await loadCompanies();

		   await loadJobs();

		   await loadApplications();

		   await loadSystemLogs();

    }
    catch (error) {

        console.error(error);

        alert(
            "Failed to load dashboard"
        );
    }
}

// ==========================================================
// AUTH HEADERS
// ==========================================================

function getAuthHeaders() {

    return {

        "Content-Type":
            "application/json",

        "Authorization":
            "Bearer " + token
    };
}

// ==========================================================
// API REQUEST
// ==========================================================

async function apiRequest(
    url,
    method = "GET",
    body = null
) {

    const options = {

        method,

        headers:
            getAuthHeaders()
    };

    if (body) {

        options.body =
            JSON.stringify(body);
    }

    const response =
        await fetch(
            API_BASE_URL + url,
            options
        );

    if (!response.ok) {

        if (
            response.status === 401
        ) {

            logout();
        }

        throw new Error(
            "API Request Failed"
        );
    }

    return await response.json();
}

// ==========================================
// LOAD COMPANIES
// ==========================================

async function loadCompanies() {

    companies =
        await apiRequest(
            "/api/admin/companies"
        );

    populateCompaniesTable();
}

async function loadRecruiters() {

    recruiters =
        await apiRequest(
            "/api/admin/recruiters"
        );

    renderRecruiters();
}

// ==========================================================
// LOAD JOBS
// ==========================================================

async function loadJobs() {

    const jobs =
        await apiRequest(
            "/api/admin/jobs"
        );

    populateJobsTable(jobs);
}

// ==========================================================
// LOAD ADMIN PROFILE
// API:
// GET /api/users/me
// ==========================================================

async function loadAdminProfile() {

    adminUser =
        await apiRequest(
            "/api/users/me"
        );

    populateAdminProfile();
}

// ==========================================================
// POPULATE PROFILE
// ==========================================================

function populateAdminProfile() {

	setText(
	    "headerAdminName",
	    adminUser.firstName +
	    " " +
	    adminUser.lastName
	);

	setText(
	    "welcomeAdminName",
	    adminUser.firstName +
	    " " +
	    adminUser.lastName
	);

    setText(
        "adminName",
        adminUser.firstName +
        " " +
        adminUser.lastName
    );

    setText(
        "adminEmail",
        adminUser.email
    );

	setText(
	    "adminRole",
	    "CareerHub Platform Administrator"
	);
}

function populateCompaniesTable() {

    const tableBody =
        document.getElementById(
            "companiesTableBody"
        );

    if (!tableBody) {

        return;
    }

    tableBody.innerHTML = "";

    companies.forEach(company => {

        const row =
            document.createElement("tr");

        row.innerHTML = `

            <td>${company.companyId}</td>

            <td>${company.companyName}</td>

            <td>${company.industryType}</td>

            <td>

                <a href="${company.companyWebsite}"
                   target="_blank">

                   Visit

                </a>

            </td>

            <td>${company.companyLocation}</td>

            <td>${company.companySize}</td>

        `;

        tableBody.appendChild(row);
    });
}

function renderRecruiters() {

    const tableBody =
        document.getElementById(
            "recruitersTableBody"
        );

    if (!tableBody) {

        return;
    }

    tableBody.innerHTML = "";

    recruiters.forEach(recruiter => {

        const verifiedBadge =
            recruiter.verified
                ? "Verified"
                : "Not Verified";

        const row =
            document.createElement("tr");

        row.innerHTML = `

            <td>
                ${recruiter.recruiterProfileId}
            </td>

            <td>
                ${recruiter.recruiterName}
            </td>

            <td>
                ${recruiter.email}
            </td>

            <td>
                ${recruiter.companyName}
            </td>

            <td>
                ${recruiter.designation}
            </td>

            <td>
                ${recruiter.workLocation}
            </td>

            <td>
                ${verifiedBadge}
            </td>

            <td>

                <span class="
                    status-badge
                    ${getStatusClass(
                        recruiter.accountStatus
                    )}
                ">
                    ${recruiter.accountStatus}
                </span>

            </td>

        `;

        tableBody.appendChild(row);
    });
}

// ==========================================================
// POPULATE JOBS TABLE
// ==========================================================

function populateJobsTable(jobs) {

    const tableBody =
        document.getElementById(
            "jobsTableBody"
        );

    if (!tableBody) {

        return;
    }

    tableBody.innerHTML = "";

    jobs.forEach(job => {

        tableBody.innerHTML += `

            <tr>

                <td>${job.jobId}</td>

                <td>${job.jobTitle}</td>

                <td>${job.companyName}</td>

                <td>${job.jobLocation}</td>

                <td>${job.employmentType}</td>

                <td>

                    <span class="status-badge status-active">

                        ${job.jobStatus}

                    </span>

                </td>

            </tr>

        `;
    });
}

async function loadApplications() {

    try {

        const response = await fetch(
            "http://localhost:8080/api/admin/applications",
            {
                headers: {
                    Authorization:
                        "Bearer " + token
                }
            }
        );
		
		if (!response.ok) {

		    throw new Error(
		        "Failed to load applications"
		    );
		}

        const applications =
            await response.json();

        const tableBody =
            document.getElementById(
                "applicationsTableBody"
            );

        tableBody.innerHTML = "";

        applications.forEach(application => {

            tableBody.innerHTML += `
                <tr>
                    <td>${application.applicationId}</td>
                    <td>${application.candidateName || "N/A"}</td>
                    <td>${application.jobTitle}</td>
                    <td>${application.companyName}</td>
                    <td>
					<span class="
					    status-badge
					    ${getApplicationStatusClass(
					        application.applicationStatus
					    )}
					">
					    ${application.applicationStatus}
					</span>
                    </td>
                </tr>
            `;
        });

    } catch(error) {

        console.error(
            "Error loading applications:",
            error
        );
    }
} 
// ==========================================================
// LOAD DASHBOARD STATS
// API:
// GET /api/admin/dashboard/stats
// ==========================================================

async function loadDashboardStats() {

    dashboardStats =
        await apiRequest(
            "/api/admin/dashboard/stats"
        );

    populateStatistics();

    populateReports();

	
	document.getElementById("reportUsers").textContent =
	    dashboardStats.totalUsers;

	document.getElementById("reportCandidates").textContent =
	    dashboardStats.totalCandidates;

	document.getElementById("reportRecruiters").textContent =
	    dashboardStats.totalRecruiters;

	document.getElementById("reportCompanies").textContent =
	    dashboardStats.totalCompanies;

	document.getElementById("reportJobs").textContent =
	    dashboardStats.totalJobs;

	document.getElementById("reportApplications").textContent =
	    dashboardStats.totalApplications;

}

function populateReports() {

    setText(
        "reportUsers",
        dashboardStats.totalUsers
    );

    setText(
        "reportCandidates",
        dashboardStats.totalCandidates
    );

    setText(
        "reportRecruiters",
        dashboardStats.totalRecruiters
    );

    setText(
        "reportCompanies",
        dashboardStats.totalCompanies
    );

    setText(
        "reportJobs",
        dashboardStats.totalJobs
    );

    setText(
        "reportApplications",
        dashboardStats.totalApplications
    );
}
// ==========================================================
// POPULATE STATISTICS
// ==========================================================

function populateStatistics() {

    setText(
        "totalUsers",
        dashboardStats.totalUsers
    );

    setText(
        "totalCandidates",
        dashboardStats.totalCandidates
    );

    setText(
        "totalRecruiters",
        dashboardStats.totalRecruiters
    );

    setText(
        "totalCompanies",
        dashboardStats.totalCompanies
    );

    setText(
        "totalJobs",
        dashboardStats.totalJobs
    );

    setText(
        "totalApplications",
        dashboardStats.totalApplications
    );
}

// ==========================================================
// SIDEBAR
// ==========================================================

function initializeSidebar() {

    const sidebar =
        document.getElementById(
            "sidebar"
        );

    const menuToggle =
        document.getElementById(
            "menuToggle"
        );

    const overlay =
        document.getElementById(
            "overlay"
        );

    if (!menuToggle) {

        return;
    }

    menuToggle.addEventListener(
        "click",
        () => {

            sidebar.classList.toggle(
                "active"
            );

            overlay.classList.toggle(
                "active"
            );
        }
    );

    if (overlay) {

        overlay.addEventListener(
            "click",
            () => {

                sidebar.classList.remove(
                    "active"
                );

                overlay.classList.remove(
                    "active"
                );
            }
        );
    }
}

// ==========================================================
// THEME
// ==========================================================

function initializeTheme() {

    const themeToggle =
        document.getElementById(
            "themeToggle"
        );

    const savedTheme =
        localStorage.getItem(
            "theme"
        );

    if (
        savedTheme === "dark"
    ) {

        document.body.classList.add(
            "dark-mode"
        );

        themeToggle.innerHTML =
            '<i class="ri-sun-line"></i>';
    }

    if (!themeToggle) {

        return;
    }

    themeToggle.addEventListener(
        "click",
        toggleTheme
    );
}

function toggleTheme() {

    document.body.classList.toggle(
        "dark-mode"
    );

    const darkMode =
        document.body.classList.contains(
            "dark-mode"
        );

    localStorage.setItem(
        "theme",
        darkMode
            ? "dark"
            : "light"
    );

    const themeToggle =
        document.getElementById(
            "themeToggle"
        );

    themeToggle.innerHTML =
        darkMode
            ? '<i class="ri-sun-line"></i>'
            : '<i class="ri-moon-line"></i>';
}

// ==========================================================
// LOGOUT
// ==========================================================

function initializeLogout() {

    const logoutBtn =
        document.getElementById(
            "logoutBtn"
        );

    if (!logoutBtn) {

        return;
    }

    logoutBtn.addEventListener(
        "click",
        logout
    );
}

function logout() {

    localStorage.removeItem(
        "token"
    );

    localStorage.removeItem(
        "tokenType"
    );

    localStorage.removeItem(
        "role"
    );

    window.location.href =
        "/html/login.html";
}

// ==========================================================
// HELPER
// ==========================================================

function setText(
    id,
    value
) {

    const element =
        document.getElementById(id);

    if (element) {

        element.textContent =
            value || "-";
    }
}

// ==========================================================
// LOAD USERS
// ==========================================================

async function loadUsers() {

    try {

        const users =
            await apiRequest(
                "/api/admin/users"
            );

        renderUsers(users);
    }
    catch (error) {

        console.error(
            "Failed to load users",
            error
        );
    }
}

// ==========================================================
// RENDER USERS
// ==========================================================

function renderUsers(users) {

    const tableBody =
        document.getElementById(
            "usersTableBody"
        );

    if (!tableBody) {

        return;
    }

    if (!users.length) {

        tableBody.innerHTML =
            `
            <tr>
                <td colspan="6">
                    No users found
                </td>
            </tr>
            `;

        return;
    }

    tableBody.innerHTML = "";

    users.forEach(user => {

        tableBody.innerHTML += `

        <tr>

            <td>${user.userId}</td>

            <td>
                ${getFullName(user)}
            </td>

            <td>${user.email}</td>

            <td>${user.role}</td>

            <td>
                <span class="
                    status-badge
                    ${getStatusClass(
                        user.accountStatus
                    )}
                ">
                    ${user.accountStatus}
                </span>
            </td>

            <td>

                <div class="action-buttons">

                    <button
                        class="status-action-btn btn-active"
                        onclick="
                        updateUserStatus(
                            ${user.userId},
                            'ACTIVE'
                        )">
                        Activate
                    </button>

                    <button
                        class="status-action-btn btn-inactive"
                        onclick="
                        updateUserStatus(
                            ${user.userId},
                            'INACTIVE'
                        )">
                        Inactivate
                    </button>

                    <button
                        class="status-action-btn btn-blocked"
                        onclick="
                        updateUserStatus(
                            ${user.userId},
                            'BLOCKED'
                        )">
                        Block
                    </button>

                </div>

            </td>

        </tr>

        `;
    });
}

// ==========================================================
// UPDATE STATUS
// ==========================================================

async function updateUserStatus(
    userId,
    status
) {

    const confirmed =
        confirm(
            "Change user status to "
            + status +
            " ?"
        );

    if (!confirmed) {

        return;
    }

    try {

        await apiRequest(

            "/api/admin/users/"
            + userId +
            "/status",

            "PUT",

            {
                accountStatus:
                    status
            }
        );

        await loadUsers();

        alert(
            "Status updated successfully"
        );
    }
    catch (error) {

        console.error(error);

        alert(
            "Failed to update status"
        );
    }
}

// ==========================================================
// HELPERS
// ==========================================================

function getFullName(user) {

    const first =
        user.firstName || "";

    const last =
        user.lastName || "";

    const fullName =
        (first + " " + last).trim();

    return fullName || "-";
}

function getStatusClass(status) {

    switch (status) {

        case "ACTIVE":

            return "status-active";

        case "INACTIVE":

            return "status-inactive";

        case "BLOCKED":

            return "status-blocked";

        default:

            return "";
    }
}

function getApplicationStatusClass(status) {

    switch (status) {

        case "APPLIED":
            return "status-applied";

        case "SHORTLISTED":
            return "status-shortlisted";

        case "INTERVIEW_SCHEDULED":
            return "status-interview";

        case "REJECTED":
            return "status-rejected";

        default:
            return "status-active";
    }
}

async function loadSystemLogs() {

    try {

        const response = await fetch(
            "http://localhost:8080/api/admin/logs",
            {
                headers: {
                    Authorization:
                        "Bearer " + token
                }
            });

        const logs =
            await response.json();

        const tableBody =
            document.getElementById(
                "logsTableBody");

        tableBody.innerHTML = "";

        logs.forEach(log => {

            tableBody.innerHTML += `
                <tr>
                    <td>${log.logId}</td>
                    <td>${log.adminName || "-"}</td>
                    <td>${log.actionType}</td>
                    <td>${log.moduleName}</td>
                    <td>${log.ipAddress}</td>
                    <td>${log.createdAt}</td>
                </tr>
            `;
        });

    } catch (error) {

        console.error(
            "Error loading logs:",
            error);
    }
}

async function loadCandidates() {

    const candidates =
        await apiRequest(
            "/api/admin/candidates"
        );

    renderCandidates(candidates);
}

function renderCandidates(candidates) {

    const tableBody =
        document.getElementById(
            "candidatesTableBody"
        );

    if (!tableBody) {
        return;
    }

    tableBody.innerHTML = "";

    candidates.forEach(candidate => {

        tableBody.innerHTML += `

            <tr>

                <td>${candidate.candidateProfileId}</td>

                <td>${candidate.candidateName}</td>

                <td>${candidate.email}</td>

                <td>${candidate.currentJobTitle || "-"}</td>

                <td>${candidate.currentLocation || "-"}</td>

                <td>

                    <span class="
                        status-badge
                        ${getStatusClass(
                            candidate.accountStatus
                        )}
                    ">
                        ${candidate.accountStatus}
                    </span>

                </td>

            </tr>

        `;
    });
}

window.addEventListener(
    "scroll",
    highlightActiveSection
);

function highlightActiveSection() {

    const sections = document.querySelectorAll(
        "section[id]"
    );

    const menuItems = document.querySelectorAll(
        ".sidebar-menu .menu-item"
    );

    let currentSection = "";

    sections.forEach(section => {

        const sectionTop =
            section.offsetTop - 120;

        if (
            window.scrollY >= sectionTop
        ) {
            currentSection =
                section.getAttribute("id");
        }
    });

    menuItems.forEach(item => {

        item.classList.remove("active");

        const href =
            item.getAttribute("href");

        if (
            href &&
            href === "#" + currentSection
        ) {

            item.classList.add("active");
        }
    });
}