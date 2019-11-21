"use strict";

// DOM Elements
const editButtons = document.querySelectorAll('[data-type="edit-btn"]');

// Event Listeners
editButtons.forEach(button => {
   button.addEventListener('click', loadModalHtml);
});

// Functions
async function loadModalHtml() {

    // Local variables
    const domParser = new DOMParser();
    const id = event.currentTarget.dataset.id;

    const response = await fetch(`${window.location.origin}/employees-tables-mvc/edit-form/${id}`);
    const htmlResponse = await response.text();
    const domResponse = domParser.parseFromString(htmlResponse, 'text/html');

    console.log(domResponse);

    // Creating modal
    const modal = document.createElement('div');
    // Event listener to close modal
    modal.addEventListener('click', () => {
        if (event.target === modal){
            modal.remove()
        }
    });

    modal.classList.add('modal-background');

    const modalContent = document.createElement('div');
    modalContent.classList.add('modal-content');
    modalContent.appendChild(domResponse.querySelector('.container'));

    modal.appendChild(modalContent);

    document.querySelector('body').insertBefore(modal, document.querySelector('body').childNodes[0]);
}