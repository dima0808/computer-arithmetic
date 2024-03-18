// open nav
const btnNavel = document.querySelector(".btn-mobile-nav");
const headerEl = document.querySelector(".header");
const htmlEl = document.querySelector("html");

btnNavel.addEventListener("click", function () {
  headerEl.classList.toggle("nav-open");
  if (htmlEl.style.overflowY === "hidden") {
    htmlEl.style.overflowY = "auto";
  } else {
    htmlEl.style.overflowY = "hidden";
  }
});

function handleNewsLinkClick() {
  headerEl.classList.remove("nav-open");
  htmlEl.style.overflowY = "auto";
}

// nav`s scroll
const body = document.body;
let lastScroll = 0;

window.addEventListener("scroll", () => {
  const currentScroll = window.pageYOffset;
  if (currentScroll <= 50) {
    body.classList.remove("scroll-up");
  }
  if (currentScroll <= 180) {
    return;
  }
  if (currentScroll <= 0) {
    body.classList.remove("scroll-up");
    return;
  }

  if (currentScroll > lastScroll && !body.classList.contains("scroll-down")) {
    body.classList.remove("scroll-up");
    body.classList.add("scroll-down");
  } else if (
    currentScroll < lastScroll &&
    body.classList.contains("scroll-down")
  ) {
    body.classList.remove("scroll-down");
    body.classList.add("scroll-up");
  }
  lastScroll = currentScroll;
});
