// Title: Timestamp picker
// Description: See the demo at url
// URL: http://www.geocities.com/tspicker/
// Version: 1.0
// Date: 12-05-2001 (mm-dd-yyyy)
// Author: Denis Gritcyuk <denis@softcomplex.com>; <tspicker@yahoo.com>
// Notes: Permission given to use this script in any kind of applications if
//    header lines are left unchanged. Feel free to contact the author
//    for feature requests and/or donations

function show_date_time4twin_fields(str_target1, str_target2, str_datetime) {
	var arr_months = ["Janvier", "F�vrier", "Mars", "Avril", "Mai", "Juin",
		"Juillet", "Ao�t", "Septembre", "Octobre", "Novembre", "D�cembre"];
	var week_days = ["Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa"];
	var n_weekstart = 0; // day week starts from (normally 0 or 1)

	var dt_datetime = (str_datetime == null || str_datetime =="" ?  new Date() : str2dt4twin(str_datetime));

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
	var str_buffer = new String (
		"<table class=\"tableOutline\" cellspacing=\"0\" border=\"0\" width=\"180\" height=\"180\" bgcolor=\"#4682B4\">" +
		"<tr><td colspan=\"7\">" +
		"  <table cellspacing=\"1\" cellpadding=\"3\" border=\"0\" width=\"100%\">" +
		"    <tr>	" +
		"      <td><a href=\"javascript:show_date_time4twin_fields('" +
           str_target1 + "', '" + str_target2 + "', '" + dt2dtstr4twin(dt_prev_year) + "'+document.forms(0).time.value); " +
           "  \">" +
           "<b style='text-decoration: none; color: #87CEFA;'>&lt;&lt;</b></a></td>" +
    "      <td><a href=\"javascript:show_date_time4twin_fields('" +
      		 str_target1 + "', '" + str_target2 + "', '" + dt2dtstr4twin(dt_prev_month) + "'+document.forms(0).time.value);\">" +
      		 "<b style='text-decoration: none; color: #87CEFA;'>" +
      		 "&lt;</b></a></td>" +
		"	     <td align=\"center\" colspan=\"5\">" +
        		"<font color=\"white\" face=\"tahoma, verdana\" size=\"1\"><b>"
        		+ arr_months[dt_datetime.getMonth()] + " " + dt_datetime.getFullYear() + "</b></font></td>"+
		"      	<td align=\"right\"><a href=\"javascript:show_date_time4twin_fields('"
        		+ str_target1 + "', '" + str_target2 + "', '" + dt2dtstr4twin(dt_next_month) + "'+document.forms(0).time.value);\">" +
        		"<b style='text-decoration: none; color: #87CEFA;'> " +
        		"&gt;</b></a></td>" +
		"       <td align=\"right\"><a href=\"javascript:show_date_time4twin_fields('"
            + str_target1 + "', '" + str_target2 + "', '" + dt2dtstr4twin(dt_next_year) + "'+document.forms(0).time.value);\">"+
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
					str_buffer += "<a href=\"javascript:" + str_target1 + ".value='"
					              + dt2dtstr4twin(dt_current_day) + "' + document.forms(0).time.value; "
					              //-- populating the second field from the first one
					              + "if (" + str_target2 + ".value.length < 1){ "
					              + "" + str_target2 + ".value="
					              + "" + str_target1 + ".value; "
              					+ "} document.all['CALENDAR_DIV'].style.visibility='hidden';" + str_target1 + ".blur();\">" +
					"<font color=\"black\" face=\"tahoma, verdana\" size=\"2\">";
				else
					// print days of other months
					str_buffer += "<a href=\"javascript:" + str_target1 + ".value='"
					               + dt2dtstr4twin(dt_current_day) + "' + document.forms(0).time.value; "
					               //-- populating the second field from the first one
					               + "if (" + str_target2 + ".value.length < 1){ "
					               + "" + str_target2 + ".value="
					               + "" + str_target1 + ".value; "
              					 + "} document.all['CALENDAR_DIV'].style.visibility='hidden';" + str_target1 + ".blur();\">" +
					"<font color=\"gray\" face=\"tahoma, verdana\" size=\"2\">";
				str_buffer += dt_current_day.getDate() +"</font></a></td>";
				dt_current_day.setDate(dt_current_day.getDate()+1);
		}
		// print row footer
		str_buffer += "</tr>";
	}
	// print calendar footer
	str_buffer +=
		"<tr><td align=\"center\" colspan=\"7\" bgcolor=\"#87CEFA\">" +
		"<font color=\"White\" face=\"tahoma, verdana\" size=\"2\">" +
		"Heure : <input type=\"text\" name=\"time\" value=\"" + dt2tmstr4twin(dt_datetime) +
		"\" size=\"7\" maxlength=\"8\"></font></td></tr>" +
		"</table>" +
		"</tr></td></table>";

	//-- Fonction d�clar�e dans le fichier lq_scripts.js
  drawCalendar(str_buffer);
}



// datetime parsing and formatting routimes. modify them if you wish other datetime format
function str2dt4twin (str_datetime) {
	//Valeur d'origine au format "dd-mm-yyyy HH:MM:SS" (seulement des chiffres(digit) et un espace(space)).
	//var re_date = /^(\d+)\-(\d+)\-(\d+)\s+(\d+)\:(\d+)\:(\d+)$/;

	//Validation de la date et de l'heure au format
	//Doit �tre au format "yyyy-mm-dd HH:MM:SS"
	var re_date = /^(\d+)\-(\d+)\-(\d+)\s+(\d+)\:(\d+):(\d+)$/;
	if (re_date.test(str_datetime) != true){
	  alert("Format de date invalide : "+ str_datetime);
          return new Date();
	}

	// Valeur d'origine au format "dd-mm-yyyy HH:MM:SS"
	//return (new Date (RegExp.$3, RegExp.$2-1, RegExp.$1, RegExp.$4, RegExp.$5, RegExp.$6));

	//Nouvelle valeur au format "yyyy-mm-jj HH:MM:SS"
	return (new Date ( RegExp.$1, RegExp.$2-1, RegExp.$3, RegExp.$4, RegExp.$5, RegExp.$6));
}

function dt2dtstr4twin (dt_datetime) {
  var aMonth = "";
  aMonth = (dt_datetime.getMonth() + 1);
  if (aMonth < 10){ aMonth = "0" + aMonth; }

  var  aDay = "";
  aDay = dt_datetime.getDate();
  if (aDay < 10) { aDay = "0" + aDay; }

	return (new String (
			dt_datetime.getFullYear() + "-" + aMonth + "-" + aDay + " "));
}

function dt2tmstr4twin (dt_datetime) {
  var anHour = dt_datetime.getHours();
  if (anHour < 10) { anHour = "0" + anHour; }

  var aMinute = dt_datetime.getMinutes();
  if (aMinute < 10) { aMinute = "0" + aMinute; }

	var aSecond = dt_datetime.getSeconds();
  if (aSecond < 10) { aSecond = "0" + aSecond; }

	return (new String (
	  anHour + ":" + aMinute + ":" + aSecond ));
}

