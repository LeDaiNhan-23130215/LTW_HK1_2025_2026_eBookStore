document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("filterForm");
  const grid = document.getElementById("grid-container");
  const container = document.getElementById("active-filters");

  if (!form || !grid || !container) return;

  function fetchData() {
    const params = new URLSearchParams(new FormData(form));

    const urlParams = new URLSearchParams(window.location.search);
    const keyword = urlParams.get("keyword");

    if (keyword && keyword.trim() !== "") {
      params.set("keyword", keyword.trim());
    }

    fetch("list-book?" + params.toString(), {
      headers: { "X-Requested-With": "XMLHttpRequest" }
    })
        .then(res => res.text())
        .then(html => {
          grid.innerHTML = html;
          renderActiveFilters();
        });
  }

  function getLabelForInput(input) {
    if (input.type === "checkbox" || input.type === "radio") {
      return input.parentElement.textContent.trim();
    }
    return input.value;
  }

  function renderActiveFilters() {
    container.innerHTML = "";

    const activeFilters = [];

    // ===== LẤY FILTER TỪ FORM =====
    [...form.elements].forEach(input => {
      if (!input.name || !input.value) return;
      if (input.name === "sortBy" || input.name === "sortDir") return;

      if ((input.type === "checkbox" || input.type === "radio") && input.checked) {
        activeFilters.push({ input, label: getLabelForInput(input) });
      }

      if (input.type === "text" && input.value.trim() !== "") {
        activeFilters.push({ input, label: "🔍 " + input.value.trim() });
      }
    });

    // ===== LẤY SEARCH TỪ HEADER (keyword trên URL) =====
    const urlParams = new URLSearchParams(window.location.search);
    const keyword = urlParams.get("keyword");

    if (keyword && keyword.trim() !== "") {
      activeFilters.push({
        input: { name: "keyword", type: "text", value: keyword },
        label: "🔍 " + keyword.trim()
      });
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

    activeFilters.forEach(({ input, label }) => {
      const tag = document.createElement("div");
      tag.className = "filter-tag";
      tag.innerHTML = `
        <span>${label}</span>
        <button type="button">&times;</button>
      `;

      tag.querySelector("button").addEventListener("click", () => {
        const urlParams = new URLSearchParams(window.location.search);

        // ===== XÓA SEARCH =====
        if (input.name === "keyword") {
          urlParams.delete("keyword");
          window.location.search = urlParams.toString();
          return;
        }

        // ===== XÓA FILTER =====
        if (input.type === "checkbox" || input.type === "radio") {
          [...form.querySelectorAll(`[name="${input.name}"]`)].forEach(i => {
            if (i.value === input.value) i.checked = false;
          });
        }

        if (input.name === "free") {
          form.querySelector(`[name="free"][value=""]`).checked = true;
        }

        fetchData();
      });

      container.appendChild(tag);
    });
  }

  // ===== BẮT SỰ KIỆN FILTER =====
  form.querySelectorAll("input").forEach(input => {
    input.addEventListener("change", fetchData);
    if (input.type === "text") {
      input.addEventListener("input", fetchData);
    }
  });

  renderActiveFilters();
});
