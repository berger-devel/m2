let userId;

// Loads the list of users from the backend
function fetchUsers() {
    $.get("/user?cachebust=" + new Date(), success = (users) => {
        const table = $("#users_table").empty();
        table.append($('<tr><th>ID</th><th>Name</th><th>Pet</th><th>Favourite Breeds</th></tr>'));

        users.forEach(user => {
            const row = $('<tr></tr>');
            row.append($('<td><span>' + user.id + '</span></td>'));
            row.append($('<td><span>' + user.name + '</span></td>'));
            row.append($('<td><span>' + user.pet + '</span></td>'));
            row.append($("<td></td>").text(user.favoriteBreeds.map(breed => breed.name).sort().join(', ')));
            table.append(row);
        });
    });
}

function step2() {
    const name = $('#name').val();
    const pet = $('#pet').val();
    let method = 'POST';
    let url = '/user';

    if (userId) {
        method = 'PUT';
        url += '/' + userId;
    }

    $.ajax({
        url: url,
        type: method,
        data: JSON.stringify({ "name": name, "pet": pet}),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: (user) => {
            userId = user.id;
            fetchUsers();
        }
    });

    jQuery.get('/breed/' + pet, success = (breeds) => {
            const div = $('#step2 .hidden');
            div.removeClass('hidden');
            const table = $('#step2_table').empty();
            breeds.forEach(breed => {
                const row = $('<tr><td></td></tr>');
                const id = 'breed-' + breed.id;
                const label = $('<label></label>').attr('for', id).text(breed.name);
                const input = $('<input>').attr('id', id).attr('type', 'checkbox').addClass('breed');
                row.append(input, label);
                table.append(row);
            });
        }
    );
}

function assignBreeds() {
    const ids = [];
    $('.breed').each(function (input) {
        if ($(this).prop('checked') === true) {
            ids.push([userId, parseInt($(this).attr('id').split('-')[1])]);
        }
    });

    jQuery.ajax({
        url: '/userBreed',
        type: 'PUT',
        data: JSON.stringify(ids),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: (users) => fetchUsers()
    });

}

$(document).ready(fetchUsers);