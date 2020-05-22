var mealAjaxUrl = "ajax/profile/meals/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: "ajax/profile/meals/filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (date, type, row) {
                        if (type === "display") {
                            return moment(date).format('YYYY-MM-DD HH:MM');
                        }
                        return date;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false,
                    "render": renderEditBtn
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false,
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                $(row).attr("data-mealExcess", data.excess);
            }
        }),
        updateTable: updateFilteredTable
    });
    $('#dateTime').datetimepicker({
        theme: 'dark',
        format: 'Y-m-d H:i'
    });
    let endDate = $('#endDate');
    let startDate = $('#startDate');
    startDate.datetimepicker({
        timepicker: false,
        // onShow: function (ct) {
        //     this.setOptions({
        //         maxDate: endDate.val() ? endDate.val() : false
        //     })
        // },
        format: 'Y-m-d'
    });
    endDate.datetimepicker({
        timepicker: false,
        // onShow: function (ct) {
        //     this.setOptions({
        //         minDate: startDate.val() ? startDate.val() : false
        //     })
        // },
        format: 'Y-m-d'
    });
    $('#startTime,#endTime').datetimepicker({
        datepicker: false,
        format: 'H:i'
    });
});