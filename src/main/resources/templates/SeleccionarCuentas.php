
<?php
    if (isset($_FILES['attachments'])) {
        $msg = "";
        $targetFile = "/" . basename($_FILES['attachments']['name'][0]);
        if (file_exists($targetFile))
            $msg = array("status" => 0, "msg" => "File already exists!");
        else if (move_uploaded_file($_FILES['attachments']['tmp_name'][0], $targetFile))
            $msg = array("status" => 1, "msg" => "File Has Been Uploaded", "path" => $targetFile);

        exit(json_encode($msg));
    }
?>
<html lang="en">
  <head>
    <title> Cuentas </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Trabajo práctico D.D.S.: Cómo Invierto">
    <meta name="author" content="Grupo 6">
    
    
    <link rel="stylesheet" href="/Formatos.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
   <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>-->

    <style type="text/css">
            #dropZone {
                border: 3px dashed #0088cc;
                padding: 50px;
                width: 500px;
                margin-top: 20px;
            }

            #files {
                border: 1px dotted #0088cc;
                padding: 20px;
                width: 200px;
                display: none;
            }

            #error {
                color: red;
            }
        </style>


  </head>
  
<body>

<div class= "container-fluid bg-dark fixed-top">
    <nav class="navbar navbar-expand-lg navbar-light bg-light navbar-dark bg-dark">
        <a class="navbar-brand" href="/">Cómo Invierto</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class="navbar-nav ml-auto">
                <a class="nav-item nav-link" href="/">Cerrar sesión</a>
                <a class="nav-item nav-link" href="/cuentas">Cuentas</a>
                <a class="nav-item nav-link" href="/indicadores">Indicadores</a>
                <a class="nav-item nav-link" href="/metodologias">Metodologías</a>
            </div>
        </div>
     </nav>
</div>

<center>
        <div id="dropZone">
            <h1>Arrastrar el archivo...</h1>
            <input type="file" id="fileupload" name="attachments[]">
        </div>
            <h1 id="error"></h1><br><br>
            <h1 id="progress"></h1><br><br>
            <div id="files"></div>
</center>

        <script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
        <script src="/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
        <script src="/js/jquery.iframe-transport.js" type="text/javascript"></script>
        <script src="/js/jquery.fileupload.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
        <script type="text/javascript">
            $(function () {
               var files = $("#files");

               $("#fileupload").fileupload({
                   url: 'SeleccionarCuentas.php',
                   dropZone: '#dropZone',
                   dataType: 'json',
                   autoUpload: false
               }).on('fileuploaddone', function(e, data) {
                    var status = data.jqXHR.responseJSON.status;
                    var msg = data.jqXHR.responseJSON.msg;

                    if (status == 1) {
                        var path = data.jqXHR.responseJSON.path;
                        $("#files").fadeIn().append('<p><img style="width: 100px; height: 100px;" src="'+path+'" /></p>');
                    } else
                        $("#error").html(msg);
               }).on('fileuploadadd', function (e, data) {
                   var fileTypeAllowed = /.\.(json)$/i;
                   var fileName = data.originalFiles[0]['name'];
                   var fileSize = data.originalFiles[0]['size'];

                   if (!fileTypeAllowed.test(fileName))
                        $("#error").html('Solo se permiten archivos "empresas.json"!');
                   else {
                       $("#error").html("");
                       data.submit();
                   }
               });
            });
        </script>
    </body>
</html>