document.addEventListener("DOMContentLoaded", () => {
    const formWrapper = document.querySelector(".add-form");
    const title = document.querySelector(".add-form h2");

    title.style.cursor = "pointer";

    title.addEventListener("click", () => {
        formWrapper.classList.toggle("open");
    });
});