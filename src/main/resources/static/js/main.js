$('.add-todo').on('keypress', function (e) {
    e.preventDefault
    if (e.which === 13) {
        if ($(this).val() != '') {
            var todo = $(this).val();
            $.post("/todos/" + todo)
                .done(function (result) {
                    var id = result.id;

                    $('.add-todo').val('');

                    var markup = '<tr><td><input type="checkbox" value="" /><input type="hidden" value="' + id + '" /></td><td class="todo-name">' + todo + '</td></tr>';
                    $('#todos-table').append(markup);
                })
                .fail(function (jqXHR, textStatus, errorThrown) {
                    alert('Todos cannot be added !');
                })
        }
    }
});

$('#todos-table').on('change', 'input[type="checkbox"]', function () {
    var checkBox = $(this);
    if (checkBox.prop('checked')) {
        var doneItemId = $(this).parents('tr').find('input[type="hidden"]').val();

        $.ajax({
            url: '/todos/' + doneItemId,
            type: 'PUT',
            async: false,
            success: function (result) {
                checkBox.parent().parent().addClass('remove');
                var doneItem = checkBox.parent().parent().find('td[class="todo-name"]').text();
                done(doneItem);
            },
            error: function (error) {
                checkBox.prop('checked', false);
                alert('Todos cannot be updated !');
            }
        });

    }
});

function done(doneItem) {
    var done = doneItem;
    var markup = '<tr><td>' + doneItem + '</td></tr>';
    $('#done-items').append(markup);
    $('.remove').remove();
}