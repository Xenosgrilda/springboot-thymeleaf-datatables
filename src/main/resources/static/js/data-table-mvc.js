"use strict";

// DataTable
$(document).ready(function() {

    $('#employee-table').DataTable( {
        "paging":   false,
        "ordering": true,
        "info":     true
    } );

} );

// Events
$('.table-header-emp').on('click', (e) => {

    const theads = $('.table-header-emp');

    theads.each( (index, thead)  => {

        const icon = thead.querySelector('i.fas');

        if (e.currentTarget === thead){

            if (icon.classList.contains('fa-minus-circle') || icon.classList.contains('fa-chevron-circle-down')){
                icon.className = 'fas fa-chevron-circle-up';
            } else {
                icon.className = 'fas fa-chevron-circle-down';
            }

        } else {

            icon.className = 'fas fa-minus-circle';
        }

    })
});