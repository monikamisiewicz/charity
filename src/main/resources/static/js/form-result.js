function results() {
    let categories = document.querySelectorAll("input[type=checkbox]:checked");
    console.log("categories", categories);

    let quantityInput = document.getElementById('quantityInput').value;
    let institutionInput = document.querySelector("input[type=radio]:checked").parentElement.children[2].children[0].innerText;
    let streetInput = document.getElementById('streetInput').value;
    let cityInput = document.getElementById('cityInput').value;
    let zipcodeInput = document.getElementById('zipcodeInput').value;
    let phoneInput = document.getElementById('phoneInput').value;
    let dateInput = document.getElementById('dateInput').value;
    let timeInput = document.getElementById('timeInput').value;

    let category = document.getElementById('category');
    let quantity = document.getElementById('quantity');
    let institution = document.getElementById('institution');
    let street = document.getElementById('street');
    let city = document.getElementById('city');
    let zipcode = document.getElementById('zipcode');
    let phone = document.getElementById('phone');
    let date = document.getElementById('date');
    let time = document.getElementById('time');

    let btnNextStep = document.querySelectorAll("btn next-step");

    let cats = Array.from(categories).forEach(function (cat) {
        let catParent = cat.parentElement;
        let children = catParent.children[2];
        let childrenText = " " + children.innerText +" \n";
        category.innerText = childrenText;
        console.log("childrentext", childrenText);
    });


    quantity.innerText = quantityInput + " - ";
    institution.innerText = institutionInput;
    street.innerText = streetInput;
    city.innerText = cityInput;
    zipcode.innerText = zipcodeInput;
    phone.innerText = phoneInput;
    date.innerText = dateInput;
    time.innerText = timeInput;

}