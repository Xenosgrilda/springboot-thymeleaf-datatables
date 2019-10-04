// DataTable
    const table = $('#employee-table').DataTable( {
        paging:   true,
        ordering: true,
        info:     true,
        processing: true,
        serverSide: true,
        ajax: {
            url: '/api/employees',
            dataSrc: 'content',
            data: function (data) {
                console.log(data);
            }
        },
        columns: [
            { data : "firstName"},
            { data : "lastName"},
            { data : "email"}
        ]
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

table.on('page.dt', () => {

    const info = table.page.info();

    console.log(info);
});

// On Data Change
function changedData(data) {

    console.log()
}