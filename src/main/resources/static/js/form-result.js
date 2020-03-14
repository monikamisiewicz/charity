function results() {
    let categoryInput = document.querySelectorAll("input[name='categoryInput']:checked").value;
    let quantityInput = document.getElementById('quantityInput').value;
    let institutionInput = document.querySelector("input[name='institutionInput']:checked").value;
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

    btnNextStep.forEach(function (value) {
        value.addEventListener("click", function() {

            category.innerText = 'Ilość worków: ' + categoryInput;
            quantity.innerText = ' Przekazujesz: ' + quantityInput;
            institution.innerText = institutionInput;
            street.innerText = streetInput;
            city.innerText = cityInput;
            zipcode.innerText = zipcodeInput;
            phone.innerText = phoneInput;
            date.innerText = dateInput;
            time.innerText = timeInput;

        })
    })
}