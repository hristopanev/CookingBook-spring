const toString = ({ id, name, description, }) => {
    let columns = `
    <td class="col-md-2 text-center">#</td>
    <td class="col-md-3 text-center">${name}</td>
    <td class="col-md-2 text-center">${description}</td>
    <td class="col-md-3 text-center">
        <form class="group-form" data-id=${id} action="/groups/group/${id}" method="get">
            <button class="btn btn-info">View Group</button>
        </form>
    </td>
`
    return `<th>${columns}</th>`
};

const URLS = {
    userGroups: '/api/userGroups',
};

fetch(URLS.userGroups)
    .then(response => response.json())
    .then(userGroups => {

        let result = '';
        userGroups.forEach(group => {
            const groupString = toString(group);
            result += groupString
        });
        $('#my-groups').html(result);
    });
