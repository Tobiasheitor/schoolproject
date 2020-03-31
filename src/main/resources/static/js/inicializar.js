$(function () {

  $(document).ready(function () {
    $('.datepicker').datepicker({
      closeOnSelect: true,
      format: "dd/mm/yyyy",
      i18n: {
        months: [
          'Janeiro',
          'Fevereiro',
          'Março',
          'Abril',
          'Maio',
          'Junho',
          'Julho',
          'Agosto',
          'Setembro',
          'Outubro',
          'Novembro',
          'Dezembro'
        ],
        monthsShort: [
          'Jan',
          'Fev',
          'Mar',
          'Abr',
          'Mai',
          'Jun',
          'Jul',
          'Ago',
          'Set',
          'Out',
          'Nov',
          'Dec'
        ],
        weekdaysAbbrev: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
        weekdaysShort: [
          'Seg',
          'Ter',
          'Qua',
          'Qui',
          'Sex',
          'Sab',
          'Dom'
        ]
      }
    });
  });
})