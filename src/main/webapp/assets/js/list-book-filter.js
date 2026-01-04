document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("filterForm");
  const grid = document.getElementById("grid-container");
  const container = document.getElementById("active-filters");

  if (!form || !grid || !container) return;

  const labels = {
    category: {
      1: "Tech",
      2: "Tham khảo",
      3: "Tiếng Anh"
    },
    format: {
      PDF: "PDF",
      EPUB: "EPUB"
    },
    free: {
      true: "Miễn phí",
      false: "Có phí"
    }
  };

  function fetchData() {
    const params = new URLSearchParams(new FormData(form));

    fetch("list-book?" + params.toString(), {
      headers: { "X-Requested-With": "XMLHttpRequest" }
    })
        .then(res => res.text())
        .then(html => {
          grid.innerHTML = html;
          renderActiveFilters();
        });
  }

  function renderActiveFilters() {
    container.innerHTML = "";

    const formData = new FormData(form);
    const activeFilters = [];

    for (let [name, value] of formData.entries()) {
      if (!value || name === "sortBy" || name === "sortDir") continue;
      activeFilters.push({ name, value });
    }

    if (activeFilters.length === 0) {
      container.style.display = "none";
      return;
    }

    container.style.display = "flex";

    const title = document.createElement("p");
    title.className = "active-filter-title";
    title.textContent = "Đã lọc:";
    container.appendChild(title);

    activeFilters.forEach(({ name, value }) => {
      const label = labels[name]?.[value] || value;

      const tag = document.createElement("div");
      tag.className = "filter-tag";
      tag.innerHTML = `
        <span>${label}</span>
        <button type="button">&times;</button>
      `;

      tag.querySelector("button").addEventListener("click", () => {
        [...form.querySelectorAll(`[name="${name}"]`)].forEach(input => {
          if (input.value === value) input.checked = false;
        });

        if (name === "free") {
          form.querySelector(`[name="free"][value=""]`).checked = true;
        }

        fetchData();
      });

      container.appendChild(tag);
    });
  }

  // Auto filter on change
  form.querySelectorAll("input").forEach(input => {
    input.addEventListener("change", fetchData);
  });

  // Initial render
  renderActiveFilters();
});
