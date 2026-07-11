// ==========================================
// CAREER HUB LOGIN PAGE
// SMART JOB PORTAL
// ==========================================

// ==========================================
// DOM ELEMENTS
// ==========================================

const loginForm =
    document.getElementById("loginForm");

const emailInput =
    document.getElementById("email");

const passwordInput =
    document.getElementById("password");

const loginButton =
    document.getElementById("loginButton");

const successMessage =
    document.getElementById("successMessage");

const errorMessage =
    document.getElementById("errorMessage");

const togglePassword =
    document.getElementById("togglePassword");

const registerButton =
    document.querySelector(".register-btn");

// ==========================================
// PAGE LOAD
// ==========================================

window.addEventListener(
    "load",
    initializePage
);

function initializePage() {

    checkExistingSession();

    initializePasswordToggle();

    initializeInputAnimations();
}

// ==========================================
// SESSION CHECK
// ==========================================

async function checkExistingSession() {

    const token =
        localStorage.getItem("token");

    const role =
        localStorage.getItem("role");

    if (!token || !role) {

        return;
    }

    try {

        const response = await fetch(
            "/api/users/me",
            {
                method: "GET",

                headers: {
                    "Authorization": "Bearer " + token
                }
            }
        );

        if (!response.ok) {

            throw new Error("Invalid Session");
        }

        if (role === "ROLE_CANDIDATE") {

            window.location.href =
                "/html/candidate-dashboard.html";
        }

        else if (role === "ROLE_RECRUITER") {

            window.location.href =
                "/html/recruiter-dashboard.html";
        }

        else if (role === "ROLE_ADMIN") {

            window.location.href =
                "/html/admin-dashboard.html";
        }

    }
    catch (error) {

        console.log("Session Expired");

        localStorage.removeItem("token");
        localStorage.removeItem("role");
        localStorage.removeItem("tokenType");
    }

}

// ==========================================
// PASSWORD TOGGLE
// ==========================================

function initializePasswordToggle() {

    if (!togglePassword) {
        return;
    }

    togglePassword.addEventListener(
        "click",
        function() {

            const icon =
                togglePassword.querySelector("i");

            if (
                passwordInput.type ===
                "password"
            ) {

                passwordInput.type =
                    "text";

                icon.classList.remove(
                    "fa-eye"
                );

                icon.classList.add(
                    "fa-eye-slash"
                );

            } else {

                passwordInput.type =
                    "password";

                icon.classList.remove(
                    "fa-eye-slash"
                );

                icon.classList.add(
                    "fa-eye"
                );
            }
        }
    );
}

// ==========================================
// INPUT ANIMATIONS
// ==========================================

function initializeInputAnimations() {

    const inputs =
        document.querySelectorAll(
            "input"
        );

    inputs.forEach(input => {

        input.addEventListener(
            "focus",
            function() {

                const group =
                    this.closest(".input-group");

                if (group) {

                    group.style.transform =
                        "scale(1.01)";
                }
            }
        );

        input.addEventListener(
            "blur",
            function() {

                const group =
                    this.closest(".input-group");

                if (group) {

                    group.style.transform =
                        "scale(1)";
                }
            }
        );
    });
}

// ==========================================
// LOGIN FORM SUBMIT
// ==========================================

loginForm.addEventListener(
    "submit",
    loginUser
);

async function loginUser(event) {

    event.preventDefault();

    clearMessages();

    const email =
        emailInput.value.trim();

    const password =
        passwordInput.value.trim();

    if (!email || !password) {

        errorMessage.innerText =
            "Email and Password are required";

        return;
    }

    showLoadingState();

    const loginData = {

        email: email,

        password: password
    };

    try {

        const response =
            await fetch(
                "/api/auth/login",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body: JSON.stringify(
                        loginData
                    )
                }
            );

        if (!response.ok) {

            hideLoadingState();

            errorMessage.innerText =
                "Invalid Email or Password";

            shakeCard();

            return;
        }

        const data =
            await response.json();

        console.log("Login Response:", data);

        console.log("Token:", data.token);

        // ==================================
        // JWT STORAGE
        // ==================================

        localStorage.setItem(
            "token",
            data.token
        );

        localStorage.setItem(
            "tokenType",
            data.type
        );

        successMessage.innerText =
            "Login Successful";

        console.log(
            "JWT Saved Successfully"
        );

        console.log(
            data.token
        );

        loginButton.innerHTML =
            '<i class="fa-solid fa-circle-check"></i> Success';

        // ==================================
        // REDIRECT
        // ==================================

        setTimeout(() => {

            localStorage.setItem(
                "role",
                data.role
            );

            if (data.role === "ROLE_RECRUITER") {

                window.location.href =
                    "/html/recruiter-dashboard.html";

            }
            else if (data.role === "ROLE_CANDIDATE") {

                window.location.href =
                    "/html/candidate-dashboard.html";

            }
            else if (data.role === "ROLE_ADMIN") {

                window.location.href =
                    "/html/admin-dashboard.html";
            }
            else {

                console.error(
                    "Unknown Role:",
                    data.role
                );

                window.location.href =
                    "/html/login.html";
            }

        }, 1200);

    }
    catch (error) {

        console.error(error);

        hideLoadingState();

        errorMessage.innerText =
            "Server Error. Please try again.";

        shakeCard();
    }
}

// ==========================================
// LOADING STATE
// ==========================================

function showLoadingState() {

    loginButton.disabled = true;

    loginButton.innerHTML =
        '<i class="fa-solid fa-spinner fa-spin"></i> Signing In...';
}

function hideLoadingState() {

    loginButton.disabled = false;

    loginButton.innerHTML =
        '<i class="fa-solid fa-arrow-right-to-bracket"></i><span> Login</span>';
}

// ==========================================
// REGISTER BUTTON
// ==========================================

if (registerButton) {

    registerButton.addEventListener(
        "click",
        function() {

            window.location.href =
                "/html/register.html";


        }
    );
}

// ==========================================
// CARD SHAKE EFFECT
// ==========================================

function shakeCard() {

    const card =
        document.querySelector(
            ".login-card"
        );

    if (!card) {
        return;
    }

    card.animate(
        [
            {
                transform:
                    "translateX(0px)"
            },
            {
                transform:
                    "translateX(-10px)"
            },
            {
                transform:
                    "translateX(10px)"
            },
            {
                transform:
                    "translateX(-10px)"
            },
            {
                transform:
                    "translateX(10px)"
            },
            {
                transform:
                    "translateX(0px)"
            }
        ],
        {
            duration: 500
        }
    );
}

// ==========================================
// LOGOUT FUNCTION
// ==========================================

function logout() {

    localStorage.removeItem(
        "token"
    );

    localStorage.removeItem(
        "tokenType"
    );

    alert(
        "Logged Out Successfully"
    );

    window.location.href =
        "/html/login.html";
}

// ==========================================
// TOKEN HELPER
// ==========================================

function getToken() {

    return localStorage.getItem(
        "token"
    );
}

// ==========================================
// AUTH HEADER HELPER
// ==========================================

function getAuthHeaders() {

    const token =
        localStorage.getItem(
            "token"
        );

    return {

        "Content-Type":
            "application/json",

        "Authorization":
            "Bearer " + token
    };
}

// ==========================================
// CLEAR MESSAGES
// ==========================================

function clearMessages() {

    successMessage.innerText = "";

    errorMessage.innerText = "";
}