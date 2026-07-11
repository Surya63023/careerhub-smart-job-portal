// ==========================================================
// CAREER HUB - RECRUITER DASHBOARD
// recruiter-dashboard.js
// PART 1 - CORE FOUNDATION
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

let recruiterProfile = null;

let companyProfile = null;

let jobs = [];

let applications = [];

let aiMatches = [];

let selectedJobId = null;

let selectedCompanyId = null;

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

    initializeTheme();

    initializeSidebar();

    initializeLogout();

    initializeModals();

    showLoader();

    try {

        await loadInitialData();

    }
    catch (error) {

        console.error(error);

        showToast(
            "Failed to load dashboard",
            "error"
        );
    }
    finally {

        hideLoader();
    }
}

// ==========================================================
// LOAD INITIAL DATA
// ==========================================================

async function loadInitialData() {

    await loadRecruiterProfile();

    await loadCompanyProfile();

    await loadRecruiterJobs();

    updateStatistics();
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
// API HELPER
// ==========================================================

async function apiRequest(
    url,
    method = "GET",
    body = null
) {

    const options = {

        method: method,

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

            return;
        }

        throw new Error(
            "API Request Failed"
        );
    }

    if (
        response.status === 204
    ) {

        return null;
    }

    const contentType =
        response.headers.get(
            "content-type"
        );

    if (
        contentType &&
        contentType.includes(
            "application/json"
        )
    ) {

        return await response.json();
    }

    return await response.text();
}

// ==========================================================
// LOADER
// ==========================================================

const loadingOverlay =
    document.getElementById(
        "loadingOverlay"
    );

function showLoader() {

    if (!loadingOverlay) {

        return;
    }

    loadingOverlay.classList.remove(
        "hidden"
    );
}

function hideLoader() {

    if (!loadingOverlay) {

        return;
    }

    loadingOverlay.classList.add(
        "hidden"
    );
}

// ==========================================================
// TOAST
// ==========================================================

const toastContainer =
    document.getElementById(
        "toastContainer"
    );

function showToast(
    message,
    type = "info"
) {

    if (!toastContainer) {

        return;
    }

    const toast =
        document.createElement(
            "div"
        );

    toast.className =
        `toast toast-${type}`;

    let icon =
        "ri-information-line";

    if (type === "success") {

        icon =
            "ri-checkbox-circle-fill";
    }

    if (type === "error") {

        icon =
            "ri-close-circle-fill";
    }

    if (type === "warning") {

        icon =
            "ri-alert-fill";
    }

    toast.innerHTML = `

        <i class="${icon}"></i>

        <span>${message}</span>

    `;

    toastContainer.appendChild(
        toast
    );

    setTimeout(() => {

        toast.style.animation =
            "fadeOut 0.4s ease";

        setTimeout(() => {

            toast.remove();

        }, 400);

    }, 3000);
}

// ==========================================================
// THEME TOGGLE
// ==========================================================

const themeToggle =
    document.getElementById(
        "themeToggle"
    );

function initializeTheme() {

    const savedTheme =
        localStorage.getItem(
            "theme"
        );

    if (
        savedTheme ===
        "dark"
    ) {

        document.body.classList.add(
            "dark-mode"
        );

        updateThemeIcon(
            true
        );
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

    const isDarkMode =
        document.body.classList.contains(
            "dark-mode"
        );

    localStorage.setItem(
        "theme",
        isDarkMode
            ? "dark"
            : "light"
    );

    updateThemeIcon(
        isDarkMode
    );
}

function updateThemeIcon(
    darkMode
) {

    if (!themeToggle) {

        return;
    }

    themeToggle.innerHTML =
        darkMode
            ? '<i class="ri-sun-line"></i>'
            : '<i class="ri-moon-line"></i>';
}

// ==========================================================
// SIDEBAR
// ==========================================================

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

function initializeSidebar() {

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
// MODALS
// ==========================================================

function initializeModals() {

    initializeModal(
        "jobModal",
        "closeJobModal"
    );

    initializeModal(
        "viewJobModal",
        "closeViewJobModal"
    );

    initializeModal(
        "recruiterModal",
        "closeRecruiterModal"
    );

    initializeModal(
        "companyModal",
        "closeCompanyModal"
    );
}
function initializeModal(
    modalId,
    closeButtonId
) {

    const modal =
        document.getElementById(
            modalId
        );

    const closeButton =
        document.getElementById(
            closeButtonId
        );

    if (
        modal &&
        closeButton
    ) {

        closeButton.addEventListener(
            "click",
            () => {

                closeModal(
                    modalId
                );
            }
        );

        modal.addEventListener(
            "click",
            (event) => {

                if (
                    event.target ===
                    modal
                ) {

                    closeModal(
                        modalId
                    );
                }
            }
        );
    }
}

function openModal(
    modalId
) {

    const modal =
        document.getElementById(
            modalId
        );

    if (modal) {

        modal.classList.add(
            "active"
        );
    }
}

function closeModal(
    modalId
) {

    const modal =
        document.getElementById(
            modalId
        );

    if (modal) {

        modal.classList.remove(
            "active"
        );
    }
}

// ==========================================================
// SEARCH FILTER
// ==========================================================

const searchInput =
    document.getElementById(
        "jobSearchInput"
    );

if (searchInput) {

    searchInput.addEventListener(
        "keyup",
        handleSearch
    );
}

function handleSearch() {

    const keyword =
        searchInput.value
            .toLowerCase()
            .trim();

    const cards =
        document.querySelectorAll(
            ".job-card"
        );

    cards.forEach(card => {

        const text =
            card.innerText
                .toLowerCase();

        card.style.display =
            text.includes(
                keyword
            )
                ? "block"
                : "none";
    });
}

// ==========================================================
// DASHBOARD STATS
// ==========================================================

//function updateStatistics() {
//
//    const totalJobsElement =
//        document.getElementById(
//            "totalJobs"
//        );
//
//    const activeJobsElement =
//        document.getElementById(
//            "activeJobs"
//        );
//
//    const totalApplicationsElement =
//        document.getElementById(
//            "totalApplications"
//        );
//
//    const shortlistedCountElement =
//        document.getElementById(
//            "shortlistedCount"
//        );
//
//    if (
//        totalJobsElement
//    ) {
//
//        totalJobsElement.textContent =
//            jobs.length;
//    }
//
//    if (
//        activeJobsElement
//    ) {
//
//        const activeJobs =
//            jobs.filter(job =>
//                job.jobStatus ===
//                "OPEN"
//            ).length;
//
//        activeJobsElement.textContent =
//            activeJobs;
//    }
//
//    if (
//        totalApplicationsElement
//    ) {
//
//        totalApplicationsElement.textContent =
//            applications.length;
//    }
//
//    if (
//        shortlistedCountElement
//    ) {
//
//        const shortlisted =
//            applications.filter(
//                app =>
//                    app.applicationStatus ===
//                    "SHORTLISTED"
//            ).length;
//
//        shortlistedCountElement.textContent =
//            shortlisted;
//    }
//}

// ==========================================================
// PLACEHOLDERS FOR NEXT PARTS
// ==========================================================

async function loadRecruiterProfile() {}

async function loadCompanyProfile() {}

async function loadRecruiterJobs() {}

async function loadApplications() {}

async function loadAIMatching() {}

// ==========================================================
// RECRUITER DASHBOARD
// PART 2 - RECRUITER PROFILE
// ==========================================================

// ==========================================================
// LOAD RECRUITER PROFILE
// API:
// GET /api/recruiters/profile
// ==========================================================

async function loadRecruiterProfile() {

    try {

        recruiterProfile =
            await apiRequest(
                "/api/recruiters/profile"
            );

        if (!recruiterProfile) {

            showToast(
                "Recruiter profile not found",
                "warning"
            );

            return;
        }

        populateRecruiterProfile();

    }
    catch (error) {

        console.error(
            "Recruiter Profile Error:",
            error
        );

        showToast(
            "Failed to load recruiter profile",
            "error"
        );
    }
}

// ==========================================================
// POPULATE RECRUITER PROFILE
// ==========================================================

function populateRecruiterProfile() {

    // =====================================
    // HEADER
    // =====================================

    const headerRecruiterName =
        document.getElementById(
            "headerRecruiterName"
        );

    if (headerRecruiterName) {

        headerRecruiterName.textContent =
            recruiterProfile.fullName ||
            "Recruiter";
    }

    // =====================================
    // WELCOME BANNER
    // =====================================

    const welcomeRecruiterName =
        document.getElementById(
            "welcomeRecruiterName"
        );

    const welcomeDesignation =
        document.getElementById(
            "welcomeDesignation"
        );

    const recruiterVerificationStatus =
        document.getElementById(
            "recruiterVerificationStatus"
        );

    if (welcomeRecruiterName) {

        welcomeRecruiterName.textContent =
            recruiterProfile.fullName ||
            "Recruiter";
    }

    if (welcomeDesignation) {

        welcomeDesignation.textContent =
            recruiterProfile.designation
                ? recruiterProfile.designation +
                " • " +
                recruiterProfile.companyName
                : recruiterProfile.companyName;
    }

    if (
        recruiterVerificationStatus
    ) {

        recruiterVerificationStatus.textContent =
            recruiterProfile.isVerified
                ? "VERIFIED"
                : "NOT VERIFIED";
    }

    // =====================================
    // PROFILE CARD
    // =====================================

    setText(
        "recruiterName",
        recruiterProfile.fullName
    );

    setText(
        "recruiterEmail",
        recruiterProfile.email
    );

    setText(
        "designation",
        recruiterProfile.designation
    );

    setText(
        "department",
        recruiterProfile.department
    );

    setText(
        "yearsExperience",
        recruiterProfile.yearsOfExperience
            ? recruiterProfile.yearsOfExperience +
            " Years"
            : "-"
    );

    setText(
        "workLocation",
        recruiterProfile.workLocation
    );
}

// ==========================================================
// HELPER
// ==========================================================

function setText(
    id,
    value
) {

    const element =
        document.getElementById(
            id
        );

    if (element) {

        element.textContent =
            value || "-";
    }
}

// ==========================================================
// EDIT PROFILE BUTTON
// ==========================================================

const editRecruiterBtn =
    document.getElementById(
        "editRecruiterBtn"
    );

if (editRecruiterBtn) {

    editRecruiterBtn.addEventListener(
        "click",
        openRecruiterModal
    );
}

// ==========================================================
// OPEN RECRUITER MODAL
// ==========================================================

function openRecruiterModal() {

    if (!recruiterProfile) {

        showToast(
            "Recruiter profile not loaded",
            "warning"
        );

        return;
    }

    document.getElementById(
        "recruiterCompanyName"
    ).value =
        recruiterProfile.companyName || "";

    document.getElementById(
        "recruiterDesignation"
    ).value =
        recruiterProfile.designation || "";

    document.getElementById(
        "recruiterDepartment"
    ).value =
        recruiterProfile.department || "";

    document.getElementById(
        "recruiterExperience"
    ).value =
        recruiterProfile.yearsOfExperience || "";

    document.getElementById(
        "recruiterLocation"
    ).value =
        recruiterProfile.workLocation || "";

    document.getElementById(
        "recruiterLinkedin"
    ).value =
        recruiterProfile.linkedinUrl || "";

    document.getElementById(
        "recruiterBio"
    ).value =
        recruiterProfile.recruiterBio || "";

    openModal(
        "recruiterModal"
    );
}

// ==========================================================
// UPDATE RECRUITER PROFILE
// API:
// PUT /api/recruiters/profile
// ==========================================================

const recruiterForm =
    document.getElementById(
        "recruiterForm"
    );

if (recruiterForm) {

    recruiterForm.addEventListener(
        "submit",
        updateRecruiterProfile
    );
}

async function updateRecruiterProfile(
    event
) {

    event.preventDefault();

    const requestBody = {

        companyName:
            document.getElementById(
                "recruiterCompanyName"
            ).value.trim(),

        designation:
            document.getElementById(
                "recruiterDesignation"
            ).value.trim(),

        department:
            document.getElementById(
                "recruiterDepartment"
            ).value.trim(),

        yearsOfExperience:
            parseFloat(
                document.getElementById(
                    "recruiterExperience"
                ).value
            ) || 0,

        workLocation:
            document.getElementById(
                "recruiterLocation"
            ).value.trim(),

        linkedinUrl:
            document.getElementById(
                "recruiterLinkedin"
            ).value.trim(),

        recruiterBio:
            document.getElementById(
                "recruiterBio"
            ).value.trim()
    };

    try {

        showLoader();

        recruiterProfile =
            await apiRequest(
                "/api/recruiters/profile",
                "PUT",
                requestBody
            );

        populateRecruiterProfile();

        closeModal(
            "recruiterModal"
        );

        showToast(
            "Recruiter profile updated successfully",
            "success"
        );
    }
    catch (error) {

        console.error(error);

        showToast(
            "Failed to update recruiter profile",
            "error"
        );
    }
    finally {

        hideLoader();
    }
}

// ==========================================================
// CREATE PROFILE SUPPORT
// OPTIONAL
// API:
// POST /api/recruiters/profile
// ==========================================================

async function createRecruiterProfile(
    requestBody
) {

    try {

        showLoader();

        recruiterProfile =
            await apiRequest(
                "/api/recruiters/profile",
                "POST",
                requestBody
            );

        populateRecruiterProfile();

        showToast(
            "Recruiter profile created successfully",
            "success"
        );
    }
    catch (error) {

        console.error(error);

        showToast(
            "Failed to create recruiter profile",
            "error"
        );
    }
    finally {

        hideLoader();
    }
}

// ==========================================================
// RECRUITER DASHBOARD
// PART 3 - COMPANY MANAGEMENT
// ==========================================================

// ==========================================================
// LOAD COMPANY PROFILE
// API:
// GET /api/companies/my-company
// ==========================================================

async function loadCompanyProfile() {

    try {

        companyProfile =
            await apiRequest(
                "/api/companies/my-company"
            );

        if (!companyProfile) {

            showToast(
                "Company profile not found",
                "warning"
            );

            return;
        }

        selectedCompanyId =
            companyProfile.companyId;

        populateCompanyProfile();
    }
    catch (error) {

        console.error(
            "Company Load Error:",
            error
        );

        showToast(
            "Failed to load company profile",
            "error"
        );
    }
}

// ==========================================================
// POPULATE COMPANY CARD
// ==========================================================

function populateCompanyProfile() {

    if (!companyProfile) {

        return;
    }

    setText(
        "companyName",
        companyProfile.companyName
    );

    setText(
        "companyEmail",
        companyProfile.companyEmail
    );

    setText(
        "companyPhone",
        companyProfile.companyPhone
    );

    setText(
        "companyWebsite",
        companyProfile.companyWebsite
    );

    setText(
        "industryType",
        companyProfile.industryType
    );

    setText(
        "companySize",
        companyProfile.companySize
    );

    setText(
        "foundedYear",
        companyProfile.foundedYear
    );

    setText(
        "headquartersLocation",
        companyProfile.headquartersLocation
    );

    setText(
        "companyDescription",
        companyProfile.companyDescription
    );

    // =====================================
    // COMPANY VERIFICATION BADGE
    // =====================================

    const companyVerificationStatus =
        document.getElementById(
            "companyVerificationStatus"
        );

    if (
        companyVerificationStatus
    ) {

        companyVerificationStatus.textContent =
            companyProfile.isVerified
                ? "VERIFIED"
                : "NOT VERIFIED";

        companyVerificationStatus.className =
            companyProfile.isVerified
                ? "status-badge status-open"
                : "status-badge status-closed";
    }

    // =====================================
    // COMPANY LOGO
    // =====================================

    const companyLogo =
        document.getElementById(
            "companyLogo"
        );

    if (
        companyLogo &&
        companyProfile.companyLogo
    ) {

        companyLogo.src =
            companyProfile.companyLogo;
    }
}

// ==========================================================
// EDIT COMPANY BUTTON
// ==========================================================

const editCompanyBtn =
    document.getElementById(
        "editCompanyBtn"
    );

if (editCompanyBtn) {

    editCompanyBtn.addEventListener(
        "click",
        openCompanyModal
    );
}

// ==========================================================
// OPEN COMPANY MODAL
// ==========================================================

function openCompanyModal() {

    if (!companyProfile) {

        showToast(
            "Company profile not loaded",
            "warning"
        );

        return;
    }

    document.getElementById(
        "companyNameInput"
    ).value =
        companyProfile.companyName || "";

    document.getElementById(
        "companyEmailInput"
    ).value =
        companyProfile.companyEmail || "";

    document.getElementById(
        "companyPhoneInput"
    ).value =
        companyProfile.companyPhone || "";

    document.getElementById(
        "companyWebsiteInput"
    ).value =
        companyProfile.companyWebsite || "";

    document.getElementById(
        "industryTypeInput"
    ).value =
        companyProfile.industryType || "";

    document.getElementById(
        "companySizeInput"
    ).value =
        companyProfile.companySize || "";

    document.getElementById(
        "foundedYearInput"
    ).value =
        companyProfile.foundedYear || "";

    document.getElementById(
        "headquartersLocationInput"
    ).value =
        companyProfile.headquartersLocation || "";

    document.getElementById(
        "companyDescriptionInput"
    ).value =
        companyProfile.companyDescription || "";

    document.getElementById(
        "companyModal"
    ).classList.add(
        "active"
    );
}


// ==========================================================
// COMPANY FORM
// ==========================================================

const companyForm =
    document.getElementById(
        "companyForm"
    );

if (companyForm) {

    companyForm.addEventListener(
        "submit",
        updateCompanyProfile
    );
}

// ==========================================================
// UPDATE COMPANY
// API:
// PUT /api/companies/{companyId}
// ==========================================================

async function updateCompanyProfile(
    event
) {

    event.preventDefault();

    if (!selectedCompanyId) {

        showToast(
            "Company ID not found",
            "error"
        );

        return;
    }

    const requestBody = {

        companyName:
            document.getElementById(
                "companyNameInput"
            ).value.trim(),

        companyEmail:
            document.getElementById(
                "companyEmailInput"
            ).value.trim(),

        companyPhone:
            document.getElementById(
                "companyPhoneInput"
            ).value.trim(),

        companyWebsite:
            document.getElementById(
                "companyWebsiteInput"
            ).value.trim(),

        companyDescription:
            document.getElementById(
                "companyDescriptionInput"
            ).value.trim(),

        industryType:
            document.getElementById(
                "industryTypeInput"
            ).value.trim(),

        companySize:
            document.getElementById(
                "companySizeInput"
            ).value,

        foundedYear:
            parseInt(
                document.getElementById(
                    "foundedYearInput"
                ).value
            ),

        headquartersLocation:
            document.getElementById(
                "headquartersLocationInput"
            ).value.trim(),

    };

    if (
        !validateCompanyData(
            requestBody
        )
    ) {

        return;
    }

    try {

        showLoader();

        companyProfile =
            await apiRequest(
                `/api/companies/${selectedCompanyId}`,
                "PUT",
                requestBody
            );

        populateCompanyProfile();

        closeModal(
            "companyModal"
        );

        showToast(
            "Company updated successfully",
            "success"
        );
    }
    catch (error) {

        console.error(error);

        showToast(
            "Failed to update company",
            "error"
        );
    }
    finally {

        hideLoader();
    }
}

// ==========================================================
// COMPANY VALIDATION
// ==========================================================

function validateCompanyData(
    company
) {

    if (
        !company.companyName
    ) {

        showToast(
            "Company name is required",
            "warning"
        );

        return false;
    }

    if (
        !company.companyEmail
    ) {

        showToast(
            "Company email is required",
            "warning"
        );

        return false;
    }

    if (
        !isValidEmail(
            company.companyEmail
        )
    ) {

        showToast(
            "Invalid company email",
            "warning"
        );

        return false;
    }

    if (
        !company.companyPhone
    ) {

        showToast(
            "Company phone is required",
            "warning"
        );

        return false;
    }

    return true;
}

// ==========================================================
// EMAIL VALIDATION
// ==========================================================

function isValidEmail(
    email
) {

    const regex =
        /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    return regex.test(
        email
    );
}

// ==========================================================
// OPTIONAL CREATE COMPANY
// API:
// POST /api/companies
// ==========================================================

async function createCompany(
    requestBody
) {

    try {

        showLoader();

        companyProfile =
            await apiRequest(
                "/api/companies",
                "POST",
                requestBody
            );

        selectedCompanyId =
            companyProfile.companyId;

        populateCompanyProfile();

        showToast(
            "Company created successfully",
            "success"
        );
    }
    catch (error) {

        console.error(error);

        showToast(
            "Failed to create company",
            "error"
        );
    }
    finally {

        hideLoader();
    }
}

// ==========================================================
// REFRESH COMPANY
// ==========================================================

async function refreshCompanyProfile() {

    await loadCompanyProfile();
}

// ==========================================================
// COMPANY QUICK ACTION
// ==========================================================

const companyRefreshBtn =
    document.getElementById(
        "refreshCompanyBtn"
    );

if (companyRefreshBtn) {

    companyRefreshBtn.addEventListener(
        "click",
        refreshCompanyProfile
    );
}

// ==========================================================
// RECRUITER DASHBOARD
// PART 4 - JOB MANAGEMENT
// ==========================================================

// ==========================================================
// JOB ELEMENTS
// ==========================================================

const jobsContainer =
    document.getElementById(
        "jobsContainer"
    );

const createJobBtn =
    document.getElementById(
        "createJobBtn"
    );

const jobForm =
    document.getElementById(
        "jobForm"
    );

let editingJobId = null;

// ==========================================================
// INITIALIZE JOB EVENTS
// ==========================================================

if (createJobBtn) {

    createJobBtn.addEventListener(
        "click",
        openCreateJobModal
    );
}

if (jobForm) {

    jobForm.addEventListener(
        "submit",
        saveJob
    );
}

// ==========================================================
// LOAD RECRUITER JOBS
// API:
// GET /api/jobs/my-jobs
// ==========================================================

async function loadRecruiterJobs() {

    try {

        jobs =
            await apiRequest(
                "/api/jobs/my-jobs"
            );

        if (!Array.isArray(jobs)) {

            jobs = [];
        }

        renderJobs();

        updateStatistics();
    }
    catch (error) {

        console.error(
            "Load Jobs Error:",
            error
        );

        showToast(
            "Failed to load jobs",
            "error"
        );
    }
}

// ==========================================================
// RENDER JOBS
// ==========================================================

function renderJobs() {

    if (!jobsContainer) {

        return;
    }

    if (jobs.length === 0) {

        jobsContainer.innerHTML = `

            <div class="empty-state">

                <i class="ri-briefcase-line"></i>

                <h3>No Jobs Posted</h3>

                <p>Create your first job posting.</p>

            </div>

        `;

        return;
    }

    jobsContainer.innerHTML =
        jobs.map(job =>
            createJobCard(job)
        ).join("");
}

// ==========================================================
// JOB CARD TEMPLATE
// ==========================================================

function createJobCard(job) {

    const skills =
        job.requiredSkills
            ? job.requiredSkills
                .split(",")
                .map(skill => `
                    <span class="skill-tag">
                        ${skill.trim()}
                    </span>
                `)
                .join("")
            : "";

    return `

        <div class="job-card fade-in">

            <div class="job-title">
                ${job.jobTitle}
            </div>

            <div class="job-meta">

                <span>${job.jobLocation}</span>

                <span>${job.salaryPackage}</span>

                <span>${job.experienceRequired} Years</span>

            </div>

            <div class="job-description">
                ${job.jobDescription}
            </div>

            <div class="skills-container">
                ${skills}
            </div>

            <div class="job-footer">

                <button
                    class="
                        status-badge
                        ${job.jobStatus === "OPEN"
            ? "status-open"
            : "status-closed"
        }
                    "
                    onclick="viewJobDetails(${job.jobId})"
                >
                    ${job.jobStatus}
                </button>

                <div class="job-actions">

                    <button
                        class="icon-btn edit-btn"
                        title="Edit Job"
                        onclick="editJob(${job.jobId})">

                        <i class="ri-edit-line"></i>

                    </button>

                    <button
                        class="icon-btn applicants-btn"
                        title="View Applicants"
                        onclick="viewApplications(${job.jobId})">

                        <i class="ri-user-search-line"></i>

                    </button>

					<button
					    class="icon-btn ai-btn"
					    title="AI Matching"
					    onclick="viewAIMatching(${job.jobId})">

					    <i class="ri-brain-line"></i>

					</button>

                    <button
                        class="icon-btn delete-btn"
                        title="Delete Job"
                        onclick="deleteJob(${job.jobId})">

                        <i class="ri-delete-bin-line"></i>

                    </button>

                </div>

            </div>

        </div>

    `;
}

function viewJobDetails(jobId) {

    const job =
        jobs.find(
            j => j.jobId === jobId
        );

    if (!job) {

        showToast(
            "Job not found",
            "error"
        );

        return;
    }

    document.getElementById(
        "viewJobTitle"
    ).textContent =
        job.jobTitle || "-";

    document.getElementById(
        "viewJobLocation"
    ).textContent =
        job.jobLocation || "-";

    document.getElementById(
        "viewJobSalary"
    ).textContent =
        job.salaryPackage || "-";

    document.getElementById(
        "viewJobExperience"
    ).textContent =
        job.experienceRequired + " Years";

    document.getElementById(
        "viewEmploymentType"
    ).textContent =
        job.employmentType || "-";

    document.getElementById(
        "viewJobSkills"
    ).textContent =
        job.requiredSkills || "-";

    document.getElementById(
        "viewJobDescription"
    ).textContent =
        job.jobDescription || "-";

    document.getElementById(
        "viewJobModal"
    ).classList.add(
        "active"
    );
}

// ==========================================================
// OPEN CREATE JOB MODAL
// ==========================================================

function openCreateJobModal() {

    editingJobId = null;

    resetJobForm();

    const modalTitle =
        document.getElementById(
            "jobModalTitle"
        );

    if (modalTitle) {

        modalTitle.textContent =
            "Create New Job";
    }

    openModal(
        "jobModal"
    );
}

// ==========================================================
// RESET JOB FORM
// ==========================================================

function resetJobForm() {

    if (jobForm) {

        jobForm.reset();
    }

    editingJobId = null;
}

// ==========================================================
// EDIT JOB
// ==========================================================

function editJob(jobId) {

    const job =
        jobs.find(
            j => j.jobId === jobId
        );

    if (!job) {

        showToast(
            "Job not found",
            "error"
        );

        return;
    }

    editingJobId = jobId;

    const modalTitle =
        document.getElementById(
            "jobModalTitle"
        );

    if (modalTitle) {

        modalTitle.textContent =
            "Edit Job";
    }

    document.getElementById(
        "jobTitle"
    ).value =
        job.jobTitle || "";

    document.getElementById(
        "jobDescription"
    ).value =
        job.jobDescription || "";

    document.getElementById(
        "requiredSkills"
    ).value =
        job.requiredSkills || "";

    document.getElementById(
        "jobLocation"
    ).value =
        job.jobLocation || "";

    document.getElementById(
        "salaryPackage"
    ).value =
        job.salaryPackage || "";

    document.getElementById(
        "experienceRequired"
    ).value =
        job.experienceRequired || "";

    document.getElementById(
        "employmentType"
    ).value =
        job.employmentType || "";

    if (job.applicationDeadline) {

        document.getElementById(
            "applicationDeadline"
        ).value =
            job.applicationDeadline
                .substring(0, 16);
    }

    openModal(
        "jobModal"
    );
}

// ==========================================================
// SAVE JOB
// CREATE / UPDATE
// ==========================================================

async function saveJob(event) {

    event.preventDefault();

    const requestBody = {

        jobTitle:
            document.getElementById(
                "jobTitle"
            ).value.trim(),

        jobDescription:
            document.getElementById(
                "jobDescription"
            ).value.trim(),

        requiredSkills:
            document.getElementById(
                "requiredSkills"
            ).value.trim(),

        jobLocation:
            document.getElementById(
                "jobLocation"
            ).value.trim(),

        salaryPackage:
            document.getElementById(
                "salaryPackage"
            ).value.trim(),

        experienceRequired:
            parseInt(
                document.getElementById(
                    "experienceRequired"
                ).value
            ),

        employmentType:
            document.getElementById(
                "employmentType"
            ).value,

        applicationDeadline:
            document.getElementById(
                "applicationDeadline"
            ).value
    };

    try {

        showLoader();

        if (editingJobId) {

            await apiRequest(
                `/api/jobs/${editingJobId}`,
                "PUT",
                requestBody
            );

            showToast(
                "Job updated successfully",
                "success"
            );
        }
        else {

            await apiRequest(
                "/api/jobs",
                "POST",
                requestBody
            );

            showToast(
                "Job created successfully",
                "success"
            );
        }

        closeModal(
            "jobModal"
        );

        await loadRecruiterJobs();
    }
    catch (error) {

        console.error(error);

        showToast(
            "Failed to save job",
            "error"
        );
    }
    finally {

        hideLoader();
    }
}

// ==========================================================
// DELETE JOB
// API:
// DELETE /api/jobs/{jobId}
// ==========================================================

async function deleteJob(jobId) {

    const confirmed =
        confirm(
            "Delete this job?"
        );

    if (!confirmed) {

        return;
    }

    try {

        showLoader();

        await apiRequest(
            `/api/jobs/${jobId}`,
            "DELETE"
        );

        showToast(
            "Job deleted successfully",
            "success"
        );

        await loadRecruiterJobs();
    }
    catch (error) {

        console.error(error);

        showToast(
            "Failed to delete job",
            "error"
        );
    }
    finally {

        hideLoader();
    }
}

// ==========================================================
// SEARCH JOBS
// ==========================================================

function filterJobs(keyword) {

    if (!keyword) {

        renderJobs();

        return;
    }

    const filteredJobs =
        jobs.filter(job =>

            job.jobTitle
                .toLowerCase()
                .includes(
                    keyword.toLowerCase()
                ) ||

            job.requiredSkills
                .toLowerCase()
                .includes(
                    keyword.toLowerCase()
                ) ||

            job.jobLocation
                .toLowerCase()
                .includes(
                    keyword.toLowerCase()
                )
        );

    jobsContainer.innerHTML =
        filteredJobs.map(job =>
            createJobCard(job)
        ).join("");
}

// ==========================================================
// SEARCH INPUT
// ==========================================================

const jobSearchInput =
    document.getElementById(
        "jobSearchInput"
    );

if (jobSearchInput) {

    jobSearchInput.addEventListener(
        "input",
        event => {

            filterJobs(
                event.target.value
            );
        }
    );
}

// ==========================================================
// APPLICATION SHORTCUT
// PART 5 IMPLEMENTS THIS
// ==========================================================

async function viewApplications(jobId) {

    selectedJobId = jobId;

    await loadAIMatches(jobId);

    await loadApplications(jobId);
}

// ==========================================================
// AI MATCHING SHORTCUT
// PART 6 IMPLEMENTS THIS
// ==========================================================

function viewAIMatching(jobId) {

    selectedJobId = jobId;

    loadAIMatching(jobId);
}

// ==========================================================
// RECRUITER DASHBOARD
// PART 5 - APPLICATION MANAGEMENT
// ==========================================================

// ==========================================================
// APPLICATION ELEMENTS
// ==========================================================

const applicationsContainer =
    document.getElementById(
        "applicationsContainer"
    );

const applicationSection =
    document.getElementById(
        "applicationsSection"
    );

let currentApplicationsJobId = null;

// ==========================================================
// LOAD APPLICATIONS
// API:
// GET /api/applications/job/{jobId}
// ==========================================================

async function loadApplications(jobId) {

    try {

        currentApplicationsJobId =
            jobId;

        showLoader();

        applications =
            await apiRequest(
                `/api/applications/job/${jobId}`
            );

        if (!Array.isArray(applications)) {

            applications = [];
        }

        renderApplications();

        updateStatistics();

        if (applicationSection) {

            applicationSection.scrollIntoView({

                behavior: "smooth",

                block: "start"
            });
        }

        showToast(
            `${applications.length} applications loaded`,
            "success"
        );
    }
    catch (error) {

        console.error(
            "Applications Load Error:",
            error
        );

        showToast(
            "Failed to load applications",
            "error"
        );
    }
    finally {

        hideLoader();
    }
}

async function loadAIMatches(jobId) {

    try {

		aiMatches =
		    await apiRequest(
		        `/api/ai-match/job/${jobId}/applied-candidates`
		    );

    }
    catch(error) {

        console.error(error);

        aiMatches = [];
    }
}

function getMatchForResume(
    resumeId
) {

    return aiMatches.find(

        match =>
            match.resumeId ===
            resumeId

    );
}
// ==========================================================
// RENDER APPLICATIONS
// ==========================================================

function renderApplications() {

    if (!applicationsContainer) {

        return;
    }

    if (applications.length === 0) {

        applicationsContainer.innerHTML = `

            <div class="empty-state">

                <i class="ri-user-search-line"></i>

                <h3>No Applications Found</h3>

                <p>
                    No candidates have applied
                    for this job yet.
                </p>

            </div>

        `;

        return;
    }

    applicationsContainer.innerHTML =
        applications
            .map(application =>
                createApplicationCard(
                    application
                )
            )
            .join("");
}

// ==========================================================
// APPLICATION CARD
// ==========================================================

function createApplicationCard(
    application
) {

    const match =
        getMatchForResume(
            application.resumeId
        );

		const score =
		    match
		        ? Math.round(
		            match.matchPercentage
		        )
		        : 0;

		const matchedSkills =
		    match
		        ? match.matchedSkills
		        : "No matched skills";

		const missingSkills =
		    match
		        ? match.missingSkills
		        : "No missing skills";

    return `

        <div class="application-card fade-in">

            <div class="application-header">

                <div class="candidate-info">

                    <h3>

                        ${application.candidateName}

                    </h3>

                    <p>

                        ${application.candidateEmail || "-"}

                    </p>

                </div>

                <span class="
                    status-badge
                    ${getApplicationStatusClass(
                        application.applicationStatus
                    )}
                ">

                    ${formatApplicationStatus(
                        application.applicationStatus
                    )}

                </span>

            </div>

            <div class="application-details">

                <div class="application-item">

                    <label>

                        Resume

                    </label>

                    <span>

                        ${application.resumeTitle}

                    </span>

                </div>

				<div class="application-item">

				    <label>

				        AI Match

				    </label>

				    <span class="
				        ai-score
				        ${
				            score >= 80
				                ? "excellent-match"
				                : score >= 60
				                    ? "good-match"
				                    : "poor-match"
				        }
				    ">

				        ${score}%

				    </span>

				</div>

				<div class="application-item">

				    <label>

				        Matched Skills

				    </label>

				    <span>

				        ${matchedSkills}

				    </span>

				</div>

				<div class="application-item">

				    <label>

				        Missing Skills

				    </label>

				    <span>

				        ${missingSkills}

				    </span>

				</div>

                <div class="application-item">

                    <label>

                        AI Match

                    </label>

                    <span>

                        ${score}%

                    </span>

                </div>

            </div>

            <div class="application-actions">

                <button
                    class="secondary-btn"
					onclick="
					viewResume(
					    '${application.fileName}'
					)
					">

                    View Resume

                </button>

                <button
                    class="secondary-btn"
					onclick="
					downloadResume(
					    '${application.fileName}'
					)
					">

                    Download Resume

                </button>

            </div>

            <div class="application-actions">

                <button
                    class="secondary-btn"
                    onclick="
                        updateApplicationStatus(
                            ${application.applicationId},
                            'UNDER_REVIEW'
                        )
                    ">

                    Review

                </button>

                <button
                    class="secondary-btn"
                    onclick="
                        updateApplicationStatus(
                            ${application.applicationId},
                            'SHORTLISTED'
                        )
                    ">

                    Shortlist

                </button>

                <button
                    class="secondary-btn"
                    onclick="
                        updateApplicationStatus(
                            ${application.applicationId},
                            'REJECTED'
                        )
                    ">

                    Reject

                </button>

            </div>

        </div>

    `;
}

// ==========================================================
// UPDATE APPLICATION STATUS
// API:
// PUT /api/applications/{applicationId}/status
// ==========================================================

async function updateApplicationStatus(
    applicationId,
    status
) {

    try {

        showLoader();

        const requestBody = {

            applicationStatus:
                status
        };

        await apiRequest(

            `/api/applications/${applicationId}/status`,

            "PUT",

            requestBody
        );

        showToast(

            `Application marked as ${status}`,

            "success"
        );

        if (currentApplicationsJobId) {

            await loadApplications(

                currentApplicationsJobId
            );
        }
    }
    catch (error) {

        console.error(
            "Update Status Error:",
            error
        );

        showToast(
            "Failed to update application status",
            "error"
        );
    }
    finally {

        hideLoader();
    }
}

// ==========================================================
// STATUS CLASS
// ==========================================================

function getApplicationStatusClass(
    status
) {

    switch (status) {

        case "UNDER_REVIEW":

            return "status-under-review";

        case "SHORTLISTED":

            return "status-shortlisted";

        case "REJECTED":

            return "status-rejected";

        case "HIRED":

            return "status-hired";

        default:

            return "status-under-review";
    }
}

// ==========================================================
// STATUS LABEL
// ==========================================================

function formatApplicationStatus(
    status
) {

    if (!status) {

        return "-";
    }

    return status

        .replaceAll("_", " ")

        .toUpperCase();
}

// ==========================================================
// DATE FORMAT
// ==========================================================

function formatDateTime(
    dateTime
) {

    if (!dateTime) {

        return "-";
    }

    try {

        return new Date(
            dateTime
        ).toLocaleString();
    }
    catch {

        return dateTime;
    }
}

function viewResume(fileName)
{
    if (!fileName)
    {
        showToast(
            "Resume not found",
            "warning"
        );

        return;
    }

    const token =
        localStorage.getItem(
            "token"
        );

    fetch(
        `/api/resume-upload/download/${encodeURIComponent(fileName)}`,
        {
            headers:
            {
                Authorization:
                    `Bearer ${token}`
            }
        }
    )
    .then(response =>
    {
        if (!response.ok)
        {
            throw new Error();
        }

        return response.blob();
    })
    .then(blob =>
    {
        const url =
            URL.createObjectURL(blob);

        window.open(
            url,
            "_blank"
        );
    })
    .catch(() =>
    {
        showToast(
            "Unable to open resume",
            "error"
        );
    });
}

function downloadResume(fileName)
{
    if (!fileName)
    {
        showToast(
            "Resume not found",
            "warning"
        );

        return;
    }

    const token =
        localStorage.getItem(
            "token"
        );

    fetch(
        `/api/resume-upload/download/${encodeURIComponent(fileName)}`,
        {
            headers:
            {
                Authorization:
                    `Bearer ${token}`
            }
        }
    )
    .then(response =>
        response.blob()
    )
    .then(blob =>
    {
        const url =
            URL.createObjectURL(blob);

        const a =
            document.createElement("a");

        a.href = url;

        a.download =
            fileName;

        document.body.appendChild(a);

        a.click();

        a.remove();

        URL.revokeObjectURL(url);
    })
    .catch(() =>
    {
        showToast(
            "Download failed",
            "error"
        );
    });
}

// ==========================================================
// APPLICATION ANALYTICS
// ==========================================================

function getApplicationStatistics() {

    const stats = {

        total: applications.length,

        underReview: 0,

        shortlisted: 0,

        rejected: 0,

        hired: 0
    };

    applications.forEach(
        application => {

            switch (
            application.applicationStatus
            ) {

                case "UNDER_REVIEW":

                    stats.underReview++;

                    break;

                case "SHORTLISTED":

                    stats.shortlisted++;

                    break;

                case "REJECTED":

                    stats.rejected++;

                    break;

                case "HIRED":

                    stats.hired++;

                    break;
            }
        }
    );

    return stats;
}

// ==========================================================
// REFRESH APPLICATIONS
// ==========================================================

async function refreshApplications() {

    if (!currentApplicationsJobId) {

        showToast(

            "Select a job first",

            "warning"
        );

        return;
    }

    await loadApplications(

        currentApplicationsJobId
    );
}

// ==========================================================
// APPLICATION REFRESH BUTTON
// ==========================================================

const refreshApplicationsBtn =
    document.getElementById(
        "refreshApplicationsBtn"
    );

if (refreshApplicationsBtn) {

    refreshApplicationsBtn.addEventListener(

        "click",

        refreshApplications
    );
}

// ==========================================================
// APPLICATION FILTER
// ==========================================================

function filterApplications(
    status
) {

    if (!applicationsContainer) {

        return;
    }

    if (
        status === "ALL"
    ) {

        renderApplications();

        return;
    }

    const filteredApplications =
        applications.filter(
            application =>

                application.applicationStatus ===
                status
        );

    applicationsContainer.innerHTML =
        filteredApplications
            .map(application =>
                createApplicationCard(
                    application
                )
            )
            .join("");

    if (
        filteredApplications.length === 0
    ) {

        applicationsContainer.innerHTML = `

            <div class="empty-state">

                <i class="ri-filter-line"></i>

                <h3>No Matching Applications</h3>

                <p>
                    No applications found for
                    selected status.
                </p>

            </div>

        `;
    }
}

// ==========================================================
// STATUS FILTER DROPDOWN
// ==========================================================

const applicationStatusFilter =
    document.getElementById(
        "applicationStatusFilter"
    );

if (applicationStatusFilter) {

    applicationStatusFilter.addEventListener(

        "change",

        event => {

            filterApplications(

                event.target.value
            );
        }
    );
}

// ==========================================================
// RECRUITER DASHBOARD
// PART 6 - AI MATCHING & DASHBOARD ANALYTICS
// ==========================================================

// ==========================================================
// AI MATCH ELEMENTS
// ==========================================================

const aiMatchingContainer =
    document.getElementById(
        "aiMatchResults"
    );

const aiMatchingSection =
    document.getElementById(
        "aiMatchSection"
    );

let currentMatchingJobId = null;

// ==========================================================
// LOAD AI MATCHING
// API:
// GET /api/ai-match/job/{jobId}
// ==========================================================

async function loadAIMatching(jobId) {

    try {

        currentMatchingJobId =
            jobId;

        showLoader();

		aiMatches =
		    await apiRequest(
		        `/api/ai-match/job/${jobId}/applied-candidates`
		    );

        if (!Array.isArray(aiMatches)) {

            aiMatches = [];
        }

        renderAIMatching();

        if (aiMatchingSection) {

            aiMatchingSection.scrollIntoView({

                behavior: "smooth",

                block: "start"
            });
        }

        showToast(
            `${aiMatches.length} matches found`,
            "success"
        );
    }
    catch (error) {

        console.error(
            "AI Matching Error:",
            error
        );

        showToast(
            "Failed to load AI matching results",
            "error"
        );
    }
    finally {

        hideLoader();
    }
}

// ==========================================================
// RENDER AI MATCHING
// ==========================================================

function renderAIMatching() {

    if (!aiMatchingContainer) {

        return;
    }

    if (aiMatches.length === 0) {

        aiMatchingContainer.innerHTML = `

            <div class="empty-state">

                <i class="ri-brain-line"></i>

                <h3>No Matching Results Found</h3>

                <p>
                    AI matching results are not available
                    for this job.
                </p>

            </div>

        `;

        return;
    }

    aiMatches.sort(

        (a, b) =>

            b.matchPercentage -
            a.matchPercentage
    );

    aiMatchingContainer.innerHTML =
        aiMatches
            .map(match =>
                createMatchCard(match)
            )
            .join("");
}

// ==========================================================
// MATCH CARD
// ==========================================================

function createMatchCard(match) {

    const matchedSkills =
        match.matchedSkills
            ? match.matchedSkills
                .split(",")
                .map(skill =>

                    `<span class="matched-skill">
                        ${skill.trim()}
                    </span>`
                )
                .join("")
            : "";

    const missingSkills =
        match.missingSkills
            ? match.missingSkills
                .split(",")
                .map(skill =>

                    `<span class="missing-skill">
                        ${skill.trim()}
                    </span>`
                )
                .join("")
            : "";

    return `

        <div class="match-card fade-in">

            <div class="match-header">

                <div>

                    <h3>
                        ${match.candidateName}
                    </h3>

                    <p class="candidate-email">
                        ${match.candidateEmail}
                    </p>

                </div>

                <div class="match-score">

                    ${Math.round(
                        match.matchPercentage
                    )}%

                </div>

            </div>

            <div class="resume-info">

                <p>
                    <strong>Resume:</strong>
                    ${match.resumeTitle}
                </p>

            </div>

            <div class="progress-wrapper">

                <div class="progress-bar">

                    <div
                        class="progress-fill"
                        style="
                            width:
                            ${match.matchPercentage}%;
                        ">
                    </div>

                </div>

            </div>

            <div class="skills-section">

                <h4>

                    Matched Skills

                </h4>

                <div class="matched-skills">

                    ${matchedSkills || "<span>-</span>"}

                </div>

            </div>

            <div class="skills-section">

                <h4>

                    Missing Skills

                </h4>

                <div class="matched-skills">

                    ${missingSkills || "<span>None</span>"}

                </div>

            </div>

            <div class="application-actions">

                <button
                    class="secondary-btn"
                    onclick="
                        viewResume(
                            '${match.fileName}'
                        )
                    ">

                    View Resume

                </button>

                <button
                    class="secondary-btn"
                    onclick="
                        downloadResume(
                            '${match.fileName}'
                        )
                    ">

                    Download Resume

                </button>

            </div>

        </div>

    `;
}

// ==========================================================
// REFRESH AI MATCHING
// ==========================================================

async function refreshAIMatching() {

    if (!currentMatchingJobId) {

        showToast(
            "Select a job first",
            "warning"
        );

        return;
    }

    await loadAIMatching(
        currentMatchingJobId
    );
}

// ==========================================================
// AI REFRESH BUTTON
// ==========================================================

const refreshMatchingBtn =
    document.getElementById(
        "refreshMatchingBtn"
    );

if (refreshMatchingBtn) {

    refreshMatchingBtn.addEventListener(

        "click",

        refreshAIMatching
    );
}

// ==========================================================
// DASHBOARD ANALYTICS
// ==========================================================

function updateStatistics() {

    updateJobStatistics();

    updateApplicationStatistics();

    updateMatchingStatistics();
}

// ==========================================================
// JOB STATS
// ==========================================================

function updateJobStatistics() {

    const totalJobs =
        jobs.length;

    const activeJobs =
        jobs.filter(job =>

            job.jobStatus ===
            "OPEN"
        ).length;

    setText(
        "totalJobs",
        totalJobs
    );

    setText(
        "activeJobs",
        activeJobs
    );
}

// ==========================================================
// APPLICATION STATS
// ==========================================================

function updateApplicationStatistics() {

    const totalApplications =
        applications.length;

    const shortlistedCount =
        applications.filter(

            app =>

                app.applicationStatus ===
                "SHORTLISTED"

        ).length;

    setText(
        "totalApplications",
        totalApplications
    );

    setText(
        "shortlistedCount",
        shortlistedCount
    );
}

// ==========================================================
// MATCHING STATS
// ==========================================================

function updateMatchingStatistics() {

    if (
        aiMatches.length === 0
    ) {

        setText(
            "averageMatchScore",
            "0%"
        );

        return;
    }

    const totalScore =
        aiMatches.reduce(

            (sum, match) =>

                sum +
                match.matchPercentage,

            0
        );

    const average =
        (
            totalScore /
            aiMatches.length
        ).toFixed(0);

    setText(
        "averageMatchScore",
        average + "%"
    );
}

// ==========================================================
// TOP MATCHED CANDIDATES
// ==========================================================

function getTopMatchedCandidates() {

    return [...aiMatches]

        .sort(

            (a, b) =>

                b.matchPercentage -
                a.matchPercentage
        )

        .slice(0, 5);
}

// ==========================================================
// EXPORT MATCH REPORT
// ==========================================================

function exportMatchReport() {

    if (
        aiMatches.length === 0
    ) {

        showToast(
            "No AI match data available",
            "warning"
        );

        return;
    }

    let csvContent =

        "Resume ID,Match Percentage,Matched Skills,Missing Skills\n";

    aiMatches.forEach(match => {

        csvContent +=

            `${match.resumeId},` +

            `${match.matchPercentage},` +

            `"${match.matchedSkills}",` +

            `"${match.missingSkills}"\n`;
    });

    const blob =
        new Blob(

            [csvContent],

            {
                type:
                    "text/csv;charset=utf-8;"
            }
        );

    const link =
        document.createElement("a");

    const url =
        URL.createObjectURL(blob);

    link.href = url;

    link.download =
        "ai-matching-report.csv";

    document.body.appendChild(
        link
    );

    link.click();

    document.body.removeChild(
        link
    );

    showToast(
        "AI Match Report Exported",
        "success"
    );
}

// ==========================================================
// EXPORT BUTTON
// ==========================================================

const exportMatchBtn =
    document.getElementById(
        "exportMatchBtn"
    );

if (exportMatchBtn) {

    exportMatchBtn.addEventListener(

        "click",

        exportMatchReport
    );
}

// ==========================================================
// DASHBOARD SUMMARY
// ==========================================================

function refreshDashboard() {

    loadRecruiterProfile();

    loadCompanyProfile();

    loadRecruiterJobs();

    updateStatistics();
}

// ==========================================================
// REFRESH DASHBOARD BUTTON
// ==========================================================

const dashboardRefreshBtn =
    document.getElementById(
        "dashboardRefreshBtn"
    );

if (dashboardRefreshBtn) {

    dashboardRefreshBtn.addEventListener(

        "click",

        refreshDashboard
    );
}

// ==========================================================
// FINAL INITIALIZATION
// ==========================================================

window.addEventListener(

    "load",

    () => {

        console.log(

            "Recruiter Dashboard Loaded Successfully"
        );

        updateStatistics();
    }
);

// ==========================================================
// END OF recruiter-dashboard.js
// ==========================================================