
function delFruit(id) {
    if(window.confirm('Are you sure you want to delete')){
        window.location.href="del?id=" + id;
    }
}