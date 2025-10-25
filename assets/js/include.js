// include.js - load header/footer into pages
async function includeHTML(id, url){
  const el = document.getElementById(id);
  if(!el) return;
  const res = await fetch(url);
  el.innerHTML = await res.text();
}
document.addEventListener('DOMContentLoaded', () => {
  includeHTML('header', '/components/header.html');
  includeHTML('footer', '/components/footer.html');
});
