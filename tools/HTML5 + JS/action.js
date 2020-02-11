function myfunction() {
    var theUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    document.getElementsByTagName("p")[0].innerHTML=xmlHttp.responseText;
}