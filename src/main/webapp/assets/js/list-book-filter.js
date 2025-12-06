document.querySelector(".selected-filters").style.display = "none";

const filterGroups = [
  { selector: ".filter:nth-child(1) input[type='checkbox']", target: ".filter-show span:nth-child(1) p" }, // Thể loại
  { selector: ".filter:nth-child(2) input[type='checkbox']", target: ".filter-show span:nth-child(2) p" }, // Giá
  { selector: ".filter:nth-child(3) input[type='checkbox']", target: ".filter-show span:nth-child(3) p" }  // File
];

filterGroups.forEach(group => {
  document.querySelectorAll(group.selector).forEach(checkbox => {
    checkbox.addEventListener("change", () => {
      updateFilters();
    });
  });
});

function updateFilters() {
  let anyChecked = false;

  filterGroups.forEach(group => {
    const checkedLabels = Array.from(document.querySelectorAll(group.selector + ":checked"))
      .map(cb => cb.closest("label").textContent.trim());
    
    const targetP = document.querySelector(group.target);
    if (checkedLabels.length > 0) {
      targetP.textContent = checkedLabels.join(", ");
      anyChecked = true;
    } else {
      targetP.textContent = "";
    }
  });

  document.querySelector(".selected-filters").style.display = anyChecked ? "flex" : "none";
}
