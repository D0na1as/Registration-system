$('#date').datepicker({
    format: "yyyy-mm-dd",
    todayBtn: "linked",
    onSelect: function(dateText) {
        $(this).change();
      }
    })
    .change(function() {
      window.location.href = "" + this.value;
});