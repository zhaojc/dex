#!/bin/bash
### ================================================================================== ###
##                                                                                      ##
##  Ce script execute l'appel pour lancer un element du differe de CDX                  ##
##                                                                                      ##
### ================================================================================== ###

export LQ_APP_NAME=cdx
export LQ_SUB_HOME=/app/applq/cdx
export LQ_CMD_HOME=/app/applq/cdx/cmd

#
# Verifie qu'il y a au moins deux parametres : (env) (job)
#
if [ $# -lt 2 ]
then
    echo Il faut au moins deux parametres
    echo "CDX_ENTRETIEN <env> ${LQ_APP_NAME} <job> [ <params job> ]"
    exit 50000
fi


#
# Le premier parametre est l'environnement
#
export LQ_SUB_ENVIRONNEMENT=$1
shift

if [ !  -d "${LQ_SUB_HOME}/${LQ_SUB_ENVIRONNEMENT}"  ]
then
    echo Environnement non valide
    echo "CDX_ENTRETIEN <env> ${LQ_APP_NAME} <job> [ <params job> ]"
    exit 50000
fi

cd ${LQ_SUB_HOME}/${LQ_SUB_ENVIRONNEMENT}

#
# Le deuxieme parametre est la tache.
#
if [ "x$1" != "x" ]
then
    export LQ_SUB_IDJOB=$1
    shift
    export LQ_SUB_PARAMS=$*
else
    echo "La tache a executer est manquante"
    echo "CDX_ENTRETIEN <env> ${LQ_APP_NAME} <job> [ <params job> ]"
    exit 50000
fi

#
# Initialisation des variables necessaire a l'execution
#
export LQ_SUB_REP_LOGS=${LQ_SUB_HOME}/logs
export LQ_SUB_LIB=${LQ_SUB_HOME}/${LQ_SUB_ENVIRONNEMENT}/lib

#
# TIMESTMP /D sous unix
#
export LQ_SUB_DATE_SYS=`date +20%y%m%d`
export LQ_SUB_DATE_AFF_SYS=`date +20%y\/%m\/%d`

#
# TIMESTMP /H sous unix
#
export LQ_SUB_HEURE_SYS=`date +%H%M%S`
export LQ_SUB_HEURE_AFF_SYS=`date +%H:%M:%S`

#
# Nom du fichier Log
#
export LQ_SUB_LOG=${LQ_SUB_REP_LOGS}/CDX_ENTRETIEN-${LQ_SUB_IDJOB}-${LQ_SUB_DATE_SYS}_${LQ_SUB_HEURE_SYS}.log

#
# Creation du CLASSPATH
#
for f in `ls -1 ${LQ_SUB_LIB}/*.*`
do
        LQ_SUB_CLASSPATH=${LQ_SUB_CLASSPATH}:$f
done
export LQ_SUB_CLASSPATH


#
# on ajuste le path
#
#export PATH=${LQ_CMD_HOME}:${PATH}
#je crois pas que cela va être nécessaire


#
# Programme Principal
#
echo > ${LQ_SUB_LOG}

echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )"     >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )"     >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )     Commande Lancee: ${LQ_SUB_IDJOB}" >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )     Parametres     : ${LQ_SUB_PARAMS}" >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )" >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )" >> ${LQ_SUB_LOG}
echo >> ${LQ_SUB_LOG}

chmod 644 ${LQ_SUB_LOG}

echo                                                 >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )    Variables d'environnements "     >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
set | grep LQ_ >> ${LQ_SUB_LOG}
echo "----------------------------------------------">> ${LQ_SUB_LOG}
echo "----------------------------------------------">> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
echo "Machine         : `hostname`"                  >> ${LQ_SUB_LOG}
echo "Code utilisateur: `whoami`"                    >> ${LQ_SUB_LOG}
echo "App             : ${LQ_APP_NAME}"                   >> ${LQ_SUB_LOG}
echo "Execution de    : ${LQ_SUB_IDJOB} ${LQ_SUB_PARAMS} " >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )    Sorties de la commande "     >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}

#
# On lance la job
#
case "${LQ_SUB_IDJOB}" in
NETTOYAGE_LOG )
	# On garde uniquement les 50 derniers jours
	./script/nettoyage-repertoire.sh ${LQ_SUB_HOME}/${LQ_SUB_ENVIRONNEMENT}/logs 50 ${LQ_SUB_PARAMS} >> ${LQ_SUB_LOG} 2>&1
	CODE_RETOUR=$?
	if [ ${CODE_RETOUR} -eq 0 ]; then
		exec ./script/nettoyage-repertoire.sh ${LQ_SUB_HOME}/logs 50 ${LQ_SUB_PARAMS} >> ${LQ_SUB_LOG} 2>&1
		CODE_RETOUR=$?
	fi
;;
esac

#CODE_RETOUR=$?

#
# On affiche le footer avec le code de retour de la tache
#
export LQ_SUB_DATE_AFF_SYS=`date +20%y\/%m\/%d`
export LQ_SUB_HEURE_AFF_SYS=`date +%H:%M:%S`
echo                                                 >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )"      >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )       Code de retour: ${CODE_RETOUR}" >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )"              >> ${LQ_SUB_LOG}
echo "CDX_ENTRETIEN ( ${LQ_SUB_DATE_AFF_SYS} ${LQ_SUB_HEURE_AFF_SYS} )"              >> ${LQ_SUB_LOG}
echo                                                 >> ${LQ_SUB_LOG}


#
# On retourne le code de retour de la job
#
exit ${CODE_RETOUR}

