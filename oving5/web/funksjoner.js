
            $(document).ready(function(){
                
                
                $('Table').DataTable( {
                "order": [[ 0, "asc" ]],
                        ajax: {
                        url: 'rest/sykler',
                        dataSrc: '',
                        
                    },
                    columns: [
                        { data: 'plassering' },
                        { data: 'batteriStatus' },
                        { data: 'ledig' },
                        { data: 'nr' }
                        
                    ]
                });
                
                
                $("#create").click(function () {
                    $.ajax({
                        url: 'rest/sykler',
                        type: 'POST',
                        data: JSON.stringify({
                            plassering: $("#plassering").val(),
                            batteriStatus: $("#batteriStatus:").val(),
                            ledig: $("#ledig").val(),
                            nr:$("#nr").val(),
                            

                            
                        }),
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        success: function(result) {
                            $('#myTable').DataTable().ajax.reload();
                        }
                    });
                }); 
                
                 $('Table2').DataTable( {
                
                        ajax: {
                        url: 'rest/sykler',
                        dataSrc: '',
                        
                    },
                    columns: [
                        { data: 'navn' },
                        { data: 'email' },
                        { data: 'plassering' }
              
                    ]
                     });
                    $("#booking").click(function () {
                    $.ajax({
                        url: 'rest/sykler',
                        type: 'POST',
                        data: JSON.stringify({
                            navn: $("#navn").val(),
                            email: $("#email:").val(),
                            parkering: $("#parkering").val(),   
                        }),
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        success: function(result) {
                            $('#myTable2').DataTable().ajax.reload();
                        }
                    });
                });
               
            });