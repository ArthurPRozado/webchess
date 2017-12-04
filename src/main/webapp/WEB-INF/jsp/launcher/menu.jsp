<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <style>
            * {
                margin: 0px;
            }

            header {
                margin: 25px 0 0 25px;
            }

            #logo {
                width: 100px;
                height: 100px;
                float: left;
            }

            .logo-content {}

            .logo-content h1 {
                font-size: 3em;
                float: left;
                margin: 20px 0 0 20px;
            }

            section {
                clear: both;
                height: 100%;
                min-height: 100%;
                display: -webkit-flex;
                display: flex;
                -webkit-align-items: center;
                align-items: center;
                -webkit-justify-content: center;
                justify-content: center;
                padding-top: 200px;
            }

            #player {
                margin-right: 30px;
            }

            footer {

                position: absolute;
                bottom: 0;
                width: 100%;
            }

            .footer-note {
                font-family: sans-serif;
                font-size: 0.65em;
            }
        </style>
    </head>

    <body>

        <header>
            <img alt="Logo xadrez" id="logo" src="https://new.uschess.org/wp-content/uploads/2017/03/cropped-US_Chess_logo_icon.png"
            />
            <div class="logo-content">
                <h1>Chess4Web</h1>
            </div>
        </header>

        <section>
            <div class="button-group">
            	<form method="get" action="http://localhost:8080/webchess/chess/init">
                	<button id="player">Jogador</button>
                </form>
                <button>Gerente</button>
            </div>
        </section>

        <footer>
            <p class="footer-note">Desenvolvido para avaliação na disciplina de Desenvolvimento de Sistemas - Arthur P Rozado - Daniel B G Marcos
                - Matheus T P Alves</p>
        </footer>

    </body>

    </html>