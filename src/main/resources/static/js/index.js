//var main = {
//      init : function () {
//          var _this = this;
//          $('#join-btn').on('click', function () {
//              _this.save();
//          });
//      },
//      save : function () {
//          var data = {
//              userName: $('#userName').val(),
//              userEmail: $('#userEmail').val(),
//              password: $('#password').val()
//          };
//
//          $.ajax({
//              type: 'POST',
//              url: '/auth/join',
//              dataType: 'json',
//              contentType: 'application/json; charset=utf-8',
//              data: JSON.stringify(data)
//          }).done(function() {
//              alert('회원가입이 완료되었습니다.');
//              window.location.href = '/';	// {1}
//          }).fail(function (error) {
//              alert(JSON.stringify(error));
//          })
//      }
//
//main.init();