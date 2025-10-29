// include.js - load header/footer into pages
async function includeHTML(id, url){
  const el = document.getElementById(id);
  if(!el) return;
  const res = await fetch(url);
  el.innerHTML = await res.text();
}
document.addEventListener('DOMContentLoaded', () => {
  includeHTML('header', '../pages/header.html');
  includeHTML('footer',  '../pages/footer.html');
});
