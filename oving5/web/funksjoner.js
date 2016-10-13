
            $(document).ready(function(){
                
                
                $('#sykkelTable').DataTable( {
                "order": [[ 1, "asc" ]],
                        searching: false,
                        paging: false,
                        info:  false,
                        ajax: {
                        url: 'rest/sykkelService/sykler',
                        dataSrc: '',
                        
                    },
                    columns: [
                        { data: 'nr'},
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
                        order: [[ 0, "asc" ]],
                        searching: false,
                        paging: false,
                        info:  false,
                        ajax: {
                        url: 'rest/sykkelService/kunder/espen/bestillinger',
                        dataSrc: '',
                        
                    },
                    columns: [
                        { data: 'dato' },
                        { data: 'tid'},
                        { data: 'kode' }
                        
                    ]
                });                
                
                $("#dragvoll").click(function(){
                    fakeReserver("Dragvoll");
                });

                $("#gløshaugen").click(function(){
                    fakeReserver("Gløshaugen");
                });
                                
                $("#midtbyen").click(function(){
                    fakeReserver("Midtbyen");
                });
                             
                $("#kalvskinnet").click(function(){
                    fakeReserver("Kalvskinnet");
                });
                
                $("#registrerSykkel").click(function(){
                   plassering = $("#sykkelPlassering").val();
                   registrerSykkel(plassering);
                   
                });
                
                $("#registrerBruker").click(function () {
                    $.ajax({
                        url: 'rest/sykkelService/kunder',
                        type: 'PUT',
                        data: JSON.stringify({
                            brukerNavn: $("#nyBrukerNavn").val(),
                            epost: $("#nyBrukerEpost").val(),
                            bestillinger: []
                        }),
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        success: function(result) {
                            $('#kundeTable').DataTable().ajax.reload();
                        }
                    });
                });
                
                $("#hentSykkel").click(function(){
                    sykkelNr = $('#hentSykkelNr').val();
                    $.ajax({
                        url: 'rest/sykkelService/sykler/' + sykkelNr + '/hent',
                        type: 'POST',
                        success: function(){
                            $("#sykkelTable").DataTable().ajax.reload();                            
                        }
                    });
                });
                
                $("#leverSykkel").click(function(){
                    sykkelNr = $('#leverSykkelNr').val();
                    sykkelPlassering = $('#leverSykkelPlassering').val();
                    $.ajax({
                        url: 'rest/sykkelService/sykler/' + sykkelNr + '/lever',
                        type: 'POST',
                        data: sykkelPlassering,
                        contentType: 'text/plain',
                        success: function(){
                            $("#sykkelTable").DataTable().ajax.reload();                            
                        }
                    });
                });
                
            });

            function registrerSykkel(plassering){
                $.ajax({
                    url: 'rest/sykkelService/sykler/',
                    type: 'PUT',
                    data: plassering,
                    dataType: 'text',
                    contentType: 'text/plain',
                    success: function(){
                        $("#sykkelTable").DataTable().ajax.reload();
                    }
                });
            }
            
            function fakeReserver(plassering){
                $("#bestillingTable").DataTable().row.add(
                        {
                            "dato" : "14:15",
                            "tid" : "23.10",
                            "kode" : "123"
                        }
                        ).draw();
            }
            
            function reserver(plassering){
                    brukerNavn = "espen";                    
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
                                                $('#sykkelTable').DataTable().ajax.reload();
                                                $('#bestillingTable').DataTable().ajax.reload();
                                            }
                                        });
                                    }
                                });
                        });
                    });
                }
            
            