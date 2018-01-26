window.addEventListener('beforeunload', function () {
    window.localStorage && window.localStorage.setItem('scroll', window.pageYOffset);
    window.localStorage && window.localStorage.setItem('pathname', window.location.pathname);
});

window.addEventListener('load', function () {
    var pathname = window.localStorage && window.localStorage.getItem('pathname');
    var scrollY = +(window.localStorage && window.localStorage.getItem('scroll'));
    if (location.pathname === pathname) {
        window.scrollTo(0, scrollY);
    } else {
        window.localStorage && window.localStorage.removeItem('pathname');
        window.localStorage && window.localStorage.removeItem('scroll');
    }
});