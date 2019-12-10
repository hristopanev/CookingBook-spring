const toString = ({ id, username, }) => {
    let columns = `
    <td>#</td>
    <td>${id}</td>
    <td>${username}</td>
    <td>
        <form class="user-form" data-id=${id} action="/users/details/${id}" method="get">
            <button class="btn btn-info">View User</button>
        </form>
    </td>
`
    return `<tr>${columns}</tr>`
};



const URLS = {
    users: '/api/users',
};


fetch(URLS.users)
    .then(response => response.json())
    .then(users => {

        let result = '';
        users.forEach(user => {
            console.log(user);
            const userString = toString(user);
            result += userString
        });

        $('#all-users').html(result);
    });
