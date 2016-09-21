
            $(document).ready(function(){
                
                
                $('#Table').DataTable( {
                "order": [[ 0, "asc" ]],
                        ajax: {
                        url: 'rest/sykkelService/sykler',
                        dataSrc: '',
                        
                    },
                    columns: [
                        { data: 'plassering' },
                        { data: 'batteriStatus' },
                        { data: 'ledig' }
                        
                    ]
                });
                
                $('#kundeTable').DataTable( {
                "order": [[ 0, "asc" ]],
                        ajax: {
                        url: 'rest/sykkelService/kunder',
                        dataSrc: '',
                        
                    },
                    columns: [
                        { data: 'brukerNavn' },
                        { data: 'epost' }
                        
                    ]
                });                
                
                $("#bestillingTable").DataTable({
                "order": [[ 0, "asc" ]],
                        ajax: {
                        url: 'rest/sykkelService/kunder/espen/bestillinger',
                        dataSrc: '',
                        
                    },
                    columns: [
                        { data: 'dato' },
                        { data: 'kode' }
                        
                    ]
                });                
                
                
                $("#create").click(function () {
                    $.ajax({
                        url: 'rest/sykkelService',
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
                
                $("#booking").click(function () {
                    $.ajax({
                        url: 'rest/sykkelService',
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


                $("#reserver").click(function(){
                    var kunde;
                    
                    $.get("rest/sykkelService/kunder/espen", function(data){
                            kunde = data;
                            var sykkel;
                            $.get("rest/sykkelService/sykler/Dragvoll", function(data){
                                sykkel = data;
                                $.ajax({
                                    url: 'rest/sykkelService/kunder/' + kunde.brukerNavn,
                                    type: 'POST',
                                    data: JSON.stringify(sykkel),
                                    contentType: 'application/json',
                                    dataType: 'json',
                                    success: function(){
                                        $.ajax({
                                             url: 'rest/sykkelService/sykler/' + sykkel.nr + '/reserver',
                                            type: 'POST',
                                            success: function(){
                                                $('#Table').DataTable().ajax.reload();
                                            }
                                        });
                                    }
                                });
                        });
                    });
                });
                
                $("#reserver2").click(function(){
                    brukerNavn = $("#brukerNavn").val();
                    plassering = $("#plassering").val();
                    
                    $.get("rest/sykkelService/kunder/" + brukerNavn, function(data){
                            kunde = data;
                            var sykkel;
                            $.get("rest/sykkelService/sykler/" + plassering, function(data){
                                sykkel = data;
                                $.ajax({
                                    url: 'rest/sykkelService/kunder/' + kunde.brukerNavn,
                                    type: 'POST',
                                    data: JSON.stringify(sykkel),
                                    contentType: 'application/json',
                                    dataType: 'json',
                                    success: function(){
                                        $.ajax({
                                             url: 'rest/sykkelService/sykler/' + sykkel.nr + '/reserver',
                                            type: 'POST',
                                            success: function(){
                                                $('#Table').DataTable().ajax.reload();
                                            }
                                        });
                                    }
                                });
                        });
                    });
                });
                
                $("#registrerSykkel").click(function(){
                   plassering = $("#sykkelPlassering").val();
                   registrerSykkel(plassering);
                   
                });
            });

            function hentKunde(kunde){
                $.get("rest/sykkelService/kunder/" + kunde);
            }

            function registrerSykkel(plassering){
                $.ajax({
                    url: 'rest/sykkelService/sykler/',
                    type: 'PUT',
                    data: plassering,
                    dataType: 'text',
                    contentType: 'text/plain'});
            }
            
            