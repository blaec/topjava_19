// $(document).ready(function () {
$(function () {
    makeEditable({
            ajaxUrl: "ajax/admin/users/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "email"
                    },
                    {
                        "data": "roles"
                    },
                    {
                        "data": "enabled"
                    },
                    {
                        "data": "registered"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ]
            })
        }
    );

    $(".active").change(function () {
        let closest = $(this).closest('tr');
        const isChecked = $(this).is(":checked");
        if (isChecked) {
            closest.removeClass("non-active");
        } else {
            closest.addClass("non-active");
        }
        let userId = closest.attr("userId");
        $.ajax({
            url: context.ajaxUrl + "update",
            type: 'POST',
            data: `id=${userId}&active=${isChecked}`
        }).done(function () {
            successNoty("Saved");
        });
    })
});