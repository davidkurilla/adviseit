window.onload = function() {
    const date = new Date();
    const elem = document.querySelector("#date");
    elem.innerHTML = date.getFullYear().toString();
}