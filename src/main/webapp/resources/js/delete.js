

document.querySelectorAll(".btn-delete").forEach(btn => {
    btn.addEventListener("click", () => {
        const bookId = btn.getAttribute("data-book-id");
        if (confirm("really delete??")) {
            document.deleteForm.bookId.value = bookId;
            document.deleteForm.submit();
        }
    })
})