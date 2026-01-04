document.addEventListener("click", function (e) {
  const link = e.target.closest(".pagination a, .sort-button");
  if (!link) return;

  e.preventDefault();

  fetch(link.href, {
    headers: { "X-Requested-With": "XMLHttpRequest" }
  })
      .then(res => res.text())
      .then(html => {
        document.getElementById("grid-container").innerHTML = html;
      });
});