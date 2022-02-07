
const navSlide = () => {
    const icon = document.querySelector('.icon');
    const nav = document.querySelector('.nav-links');
    const navLinks = document.querySelectorAll('.nav-links li');
    
    icon.addEventListener('click', ()=>{
        nav.classList.toggle('nav-active');
    });
}

navSlide();