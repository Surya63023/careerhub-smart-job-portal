// ==========================================
// CAREER HUB REGISTER PAGE
// SMART JOB PORTAL
// ==========================================

// ==========================================
// DOM ELEMENTS
// ==========================================

const registerForm =
    document.getElementById("registerForm");

const firstNameInput =
    document.getElementById("firstName");

const lastNameInput =
    document.getElementById("lastName");

const emailInput =
    document.getElementById("email");

const phoneInput =
    document.getElementById("phoneNumber");

const passwordInput =
    document.getElementById("password");

const confirmPasswordInput =
    document.getElementById("confirmPassword");

const registerButton =
    document.getElementById("registerButton");

const successMessage =
    document.getElementById("successMessage");

const errorMessage =
    document.getElementById("errorMessage");

const togglePassword =
    document.getElementById("togglePassword");

const toggleConfirmPassword =
    document.getElementById("toggleConfirmPassword");

// ==========================================
// PASSWORD RULES
// ==========================================

const ruleLength =
    document.getElementById("ruleLength");

const ruleUpper =
    document.getElementById("ruleUpper");

const ruleLower =
    document.getElementById("ruleLower");

const ruleNumber =
    document.getElementById("ruleNumber");

const ruleSpecial =
    document.getElementById("ruleSpecial");

// ==========================================
// PAGE LOAD
// ==========================================

window.addEventListener(
    "load",
    initializePage
);

function initializePage() {

    initializePasswordToggle();

    initializeConfirmPasswordToggle();

    initializePasswordValidation();
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
        function () {

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
// CONFIRM PASSWORD TOGGLE
// ==========================================

function initializeConfirmPasswordToggle() {

    if (!toggleConfirmPassword) {
        return;
    }

    toggleConfirmPassword.addEventListener(
        "click",
        function () {

            const icon =
                toggleConfirmPassword.querySelector("i");

            if (
                confirmPasswordInput.type ===
                "password"
            ) {

                confirmPasswordInput.type =
                    "text";

                icon.classList.remove(
                    "fa-eye"
                );

                icon.classList.add(
                    "fa-eye-slash"
                );

            } else {

                confirmPasswordInput.type =
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
// PASSWORD VALIDATION
// ==========================================

function initializePasswordValidation() {

    passwordInput.addEventListener(
        "input",
        validatePasswordRules
    );
}

function validatePasswordRules() {

    const password =
        passwordInput.value;

    const hasLength =
        password.length >= 8;

    const hasUpper =
        /[A-Z]/.test(password);

    const hasLower =
        /[a-z]/.test(password);

    const hasNumber =
        /[0-9]/.test(password);

    const hasSpecial =
        /[!@#$%^&*(),.?":{}|<>]/.test(password);

    updateRule(
        ruleLength,
        hasLength
    );

    updateRule(
        ruleUpper,
        hasUpper
    );

    updateRule(
        ruleLower,
        hasLower
    );

    updateRule(
        ruleNumber,
        hasNumber
    );

    updateRule(
        ruleSpecial,
        hasSpecial
    );
}

function updateRule(
    element,
    valid
) {

    if (valid) {

        element.classList.add(
            "rule-valid"
        );

        element.innerHTML =
            "✅ " +
            element.textContent.replace(
                "❌ ",
                ""
            );

    } else {

        element.classList.remove(
            "rule-valid"
        );

        element.innerHTML =
            "❌ " +
            element.textContent.replace(
                "✅ ",
                ""
            );
    }
}

// ==========================================
// REGISTER FORM SUBMIT
// ==========================================

registerForm.addEventListener(
    "submit",
    registerUser
);

async function registerUser(event) {

    event.preventDefault();

    clearMessages();

    const firstName =
        firstNameInput.value.trim();

    const lastName =
        lastNameInput.value.trim();

    const email =
        emailInput.value.trim();

    const phoneNumber =
        phoneInput.value.trim();

    const password =
        passwordInput.value.trim();

    const confirmPassword =
        confirmPasswordInput.value.trim();

    // ======================================
    // VALIDATION
    // ======================================

    if (
        !firstName ||
        !lastName ||
        !email ||
        !phoneNumber ||
        !password ||
        !confirmPassword
    ) {

        showError(
            "All fields are required."
        );

        return;
    }

    if (
        !/^[0-9]{10}$/.test(
            phoneNumber
        )
    ) {

        showError(
            "Phone Number must contain exactly 10 digits."
        );

        return;
    }

    if (
        password !==
        confirmPassword
    ) {

        showError(
            "Passwords do not match."
        );

        return;
    }

    if (
        !isStrongPassword(
            password
        )
    ) {

        showError(
            "Password does not satisfy all rules."
        );

        return;
    }

    // ======================================
    // REQUEST BODY
    // ======================================

    const registerData = {

        firstName:
            firstName,

        lastName:
            lastName,

        email:
            email,

        phoneNumber:
            phoneNumber,

        password:
            password
    };

    try {

        showLoadingState();

        const response =
            await fetch(
                "/api/auth/register",
                {
                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body: JSON.stringify(
                        registerData
                    )
                }
            );

        const responseText =
            await response.text();

        if (
            !response.ok
        ) {

            hideLoadingState();

            showError(
                responseText
            );

            return;
        }

        showSuccess(
            responseText
        );

        registerButton.innerHTML =
            '<i class="fa-solid fa-circle-check"></i> Registration Successful';

        setTimeout(
            () => {

                window.location.href =
                    "/html/login.html";

            },
            2500
        );

    } catch (error) {

        console.error(
            error
        );

        hideLoadingState();

        showError(
            "Unable to connect to server."
        );
    }
}

// ==========================================
// PASSWORD STRENGTH
// ==========================================

function isStrongPassword(
    password
) {

    return (
        password.length >= 8 &&
        /[A-Z]/.test(password) &&
        /[a-z]/.test(password) &&
        /[0-9]/.test(password) &&
        /[!@#$%^&*(),.?":{}|<>]/.test(password)
    );
}

// ==========================================
// LOADING STATE
// ==========================================

function showLoadingState() {

    registerButton.disabled =
        true;

    registerButton.innerHTML =
        '<i class="fa-solid fa-spinner fa-spin"></i> Creating Account...';
}

function hideLoadingState() {

    registerButton.disabled =
        false;

    registerButton.innerHTML =
        '<i class="fa-solid fa-user-plus"></i><span> Register</span>';
}

// ==========================================
// MESSAGE HELPERS
// ==========================================

function showSuccess(
    message
) {

    successMessage.innerText =
        message;

    errorMessage.innerText =
        "";
}

function showError(
    message
) {

    errorMessage.innerText =
        message;

    successMessage.innerText =
        "";
}

function clearMessages() {

    successMessage.innerText =
        "";

    errorMessage.innerText =
        "";
}