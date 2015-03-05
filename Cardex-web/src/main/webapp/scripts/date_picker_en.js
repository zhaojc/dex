// Title: Timestamp picker
// Description: See the demo at url
// URL: http://www.geocities.com/tspicker/
// Version: 1.0
// Date: 12-05-2001 (mm-dd-yyyy)
// Author: Denis Gritcyuk <denis@softcomplex.com>; <tspicker@yahoo.com>
// Notes: Permission given to use this script in any kind of applications if
//    header lines are left unchanged. Feel free to contact the author
//    for feature requests and/or donations

function show_calendar(str_target, str_datetime) {

	var arr_months = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];
  var week_days = ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"];
	var n_weekstart = 0; // day week starts from (normally 0 or 1)

	var dt_datetime = (str_datetime == null || str_datetime =="" ?  new Date() : str2dt(str_datetime));

  var dt_prev_year = new Date(dt_datetime);
  dt_prev_year.setYear(dt_datetime.getFullYear()-1);

  var dt_next_year = new Date(dt_datetime);
  dt_next_year.setYear(dt_datetime.getFullYear()+1);


	var dt_prev_month = new Date(dt_datetime);
	dt_prev_month.setMonth(dt_datetime.getMonth()-1);
	if (dt_datetime.getMonth()%12 != (dt_prev_month.getMonth()+1)%12) {
		dt_prev_month.setMonth(dt_datetime.getMonth());
		dt_prev_month.setDate(0);
	}
	var dt_next_month = new Date(dt_datetime);
	dt_next_month.setMonth(dt_datetime.getMonth()+1);
	if ((dt_datetime.getMonth() + 1)%12 != dt_next_month.getMonth()%12)
		dt_next_month.setDate(0);

	var dt_firstday = new Date(dt_datetime);
	dt_firstday.setDate(1);
	dt_firstday.setDate(1-(7+dt_firstday.getDay()-n_weekstart)%7);
	var dt_lastday = new Date(dt_next_month);
	dt_lastday.setDate(0);

	// html generation (feel free to tune it for your particular application)
	// print calendar header
	//-- Les styles sont déclarés dans le fichier lq_styles.css
	var str_buffer = new String (

		"<table class=\"tableOutline\" cellspacing=\"0\" border=\"0\" width=\"180\" height=\"180\">" +
		"<tr><td bgcolor=\"#4682B4\" colspan=\"7\">" +

		"  <table cellspacing=\"1\" cellpadding=\"3\" border=\"0\" width=\"100%\">" +
		"    <tr>	" +
		"      <td bgcolor=\"#4682B4\"><a href=\"javascript:show_calendar('" +
           str_target + "', '" + dt2dtstr(dt_prev_year) + "' + document.forms(0).time.value);" +
           "  \">" +
           "<b style='text-decoration: none; color: #87CEFA;'>&lt;&lt;</b></a></td>" +
    "      <td bgcolor=\"#4682B4\"><a href=\"javascript:show_calendar('" +
      		 str_target + "', '" + dt2dtstr(dt_prev_month) + "' + document.forms(0).time.value);\">" +
      		 "<b style='text-decoration: none; color: #87CEFA;'>" +
      		 "&lt;</b></a></td>" +
		"	     <td align=\"center\" bgcolor=\"#4682B4\" colspan=\"5\" nowrap>" +
        		"<font color=\"white\" face=\"tahoma, verdana\" size=\"1\"><b>"
        		+ arr_months[dt_datetime.getMonth()] + " " + dt_datetime.getFullYear() + "</b></font></td>"+
		"      	<td bgcolor=\"#4682B4\" align=\"right\"><a href=\"javascript:show_calendar('"
        		+ str_target + "', '" + dt2dtstr(dt_next_month) + "' + document.forms(0).time.value);\">" +
        		"<b style='text-decoration: none; color: #87CEFA;'> " +
        		"&gt;</b></a></td>" +
		"       <td bgcolor=\"#4682B4\" align=\"right\"><a href=\"javascript:show_calendar('"
            + str_target + "', '" + dt2dtstr(dt_next_year) + "' + document.forms(0).time.value);\">"+
            "<b style='text-decoration: none; color: #87CEFA;'>" +
            "&gt;&gt;</b></a></td>"+
    "    </tr>" +
    "  </table> " +

    "</td></tr>"
	);

	var dt_current_day = new Date(dt_firstday);
	// print weekdays titles
	str_buffer += "<tr>";
	for (var n=0; n<7; n++)
		str_buffer += "	<td align=\"right\" bgcolor=\"#87CEFA\">"+
		"<font color=\"white\" face=\"tahoma, verdana\" size=\"2\">"+
		week_days[(n_weekstart+n)%7]+"</font></td>";
	// print calendar table
	str_buffer += "</tr>";
	while (dt_current_day.getMonth() == dt_datetime.getMonth() ||
		dt_current_day.getMonth() == dt_firstday.getMonth()) {
		// print row header
		str_buffer += "<tr>";
		for (var n_current_wday=0; n_current_wday<7; n_current_wday++) {
				if (dt_current_day.getDate() == dt_datetime.getDate() &&
					dt_current_day.getMonth() == dt_datetime.getMonth())
					// print current date
					str_buffer += "	<td bgcolor=\"#FFB6C1\" align=\"right\">";
				else if (dt_current_day.getDay() == 0 || dt_current_day.getDay() == 6)
					// weekend days
					str_buffer += "	<td bgcolor=\"#DBEAF5\" align=\"right\">";
				else
					// print working days of current month
					str_buffer += "	<td bgcolor=\"white\" align=\"right\">";

				if (dt_current_day.getMonth() == dt_datetime.getMonth())
					// print days of current month
					str_buffer += "<a href=\"javascript:" + str_target +
					".value='" + dt2dtstr(dt_current_day) + "';document.all['CALENDAR_DIV'].style.visibility='hidden';document.all['selectBlockerCalendar'].style.visibility = 'hidden';" + str_target + ".blur();\" >" +
					"<font color=\"black\" face=\"tahoma, verdana\" size=\"2\">";
				else
					// print days of other months
					str_buffer += "<a href=\"javascript:" + str_target +
					".value='" + dt2dtstr(dt_current_day) + "';document.all['CALENDAR_DIV'].style.visibility='hidden';document.all['selectBlockerCalendar'].style.visibility = 'hidden';" + str_target + ".blur();\" >" +
					"<font color=\"gray\" face=\"tahoma, verdana\" size=\"2\">";
				str_buffer += dt_current_day.getDate() +"</font></a></td>";
				dt_current_day.setDate(dt_current_day.getDate()+1);
		}
		// print row footer
		str_buffer += "</tr>";
	}
	// print calendar footer
/*	str_buffer +=
		"<tr><td align=\"center\" colspan=\"7\" bgcolor=\"#87CEFA\">" +
		"<input type=\"hidden\" name=\"time\" value=\"" + dt2tmstr(dt_datetime) + "\" />" +
		"</td></tr>" +
		"</table>" +
		"</tr></td></table>";
*/
	str_buffer +=
		"</table>";
		
  //-- Fonction déclarée dans le fichier lq_scripts.js
  drawCalendar(str_buffer);
}


// datetime parsing and formatting routimes. modify them if you wish other datetime format
function str2dt (str_datetime) {
	//Masque de saisie.
	//Valeur d'origine au format "dd-mm-yyyy HH:MM:SS" (seulement des chiffres(digit) et un espace(space)).
	//var re_date = /^(\d+)\/(\d+)\/(\d+)\s+(\d+)\:(\d+)\:(\d+)$/;

	//Masque de saisie
	//Validation de la date et de l'heure au format
	//Doit être au format "mm/dd/yyyy hh:mm"
	var re_date = /^(\d+)\/(\d+)\/(\d+)$/;
	if (re_date.test(str_datetime) != true){
	  alert("Invalid Date-Time Format : "+ str_datetime);
          return new Date();
	}

	// Valeur d'origine au format "dd-mm-yyyy hh:mm:ss"
	// NE FONCTIONNE PAS BIEN pour mm-dd-yyyy, même en substituant l'ordre des paramètres.
	//return (new Date (RegExp.$3, RegExp.$2-1, RegExp.$1, RegExp.$4, RegExp.$5, RegExp.$6));

	//Nouvelle valeur au format "mm/dd/yyyy hh:mm:ss"
	// Retourne le 25 décembre à minuit sur le calendrier
	//return (new Date ( 2001, 12-1, 25, 00, 00, 01 ));
	var aDate = new Date(str_datetime);
	var aYear = aDate.getFullYear();
	var aMonth = aDate.getMonth();
	var aDay = aDate.getDate();
	return (new Date (aYear, aMonth, aDay));
}

/* */
function dt2dtstr (dt_datetime) {
  var aMonth = "";
  aMonth = (dt_datetime.getMonth() + 1);
  if (aMonth < 10){ aMonth = "0" + aMonth; }

  var  aDay = "";
  aDay = dt_datetime.getDate();
  if (aDay < 10) { aDay = "0" + aDay; }

  //alert(aDay + aMonth);
	return (new String (
		aMonth + "/" + aDay + "/" +  dt_datetime.getFullYear() ));
}


function dt2tmstr (dt_datetime) {
  /*
  var anHour = dt_datetime.getHours();
  if (anHour < 10) { anHour = "0" + anHour; }

  var aMinute = dt_datetime.getMinutes();
  if (aMinute < 10) { aMinute = "0" + aMinute; }

  var aSecond = dt_datetime.getSeconds();
  if (aSecond < 10) { aSecond = "0" + aSecond; }
  */
	return (new String ( "" ));  //-- " " + anHour + ":" + aMinute ));
}
