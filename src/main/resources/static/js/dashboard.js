const { fromEvent, of, from } = rxjs;
const { switchMap, take, catchError } = rxjs.operators;

document.addEventListener('DOMContentLoaded', () => {
    const rimuoviButton = document.getElementById('rimuoviButton');
    const messaggioDiv = document.getElementById('messaggio');
	const tipo = document.getElementById('tipo').value;
	
	let attivita = true;
	
	if (tipo == 'lezione') {
		attivita = false;
	}

    if (rimuoviButton) {
        fromEvent(rimuoviButton, 'click').pipe(
            switchMap(() => {
                const idDaRimuovere = document.getElementById('id').value;

                if (!idDaRimuovere) {
                    console.error('ID non trovato nell\'attributo data-id.');
                    messaggioDiv.textContent = 'Errore: ID non trovato.';
                    return of(null); // Interrompi l'observable
                }

				let apiUrlWithQuery;
				if (attivita) {
                	apiUrlWithQuery = `../../api/attivita/elimina?id=${idDaRimuovere}`;
				} else {
					apiUrlWithQuery = `../../api/lezione/elimina?id=${idDaRimuovere}`;
				}

                return from(fetch(apiUrlWithQuery, { method: 'DELETE' }).then(response => {
					if (attivita) {
						window.location.href = "../../app/attivita/rimuovi/success";
					} else {
						window.location.href = "../../app/lezione/rimuovi/success";
					}
                }));
            }),
            take(1)
        ).subscribe(response => {
            if (response !== null) {
                console.log('Attività rimossa con successo:', response);
				if (attivita) {
                	messaggioDiv.textContent = 'Attività rimossa con successo!';
				} else {
					messaggioDiv.textContent = 'Lezione rimossa con successo!';
				}
            }
        });
    } else {
        console.error('Elemento con ID "rimuoviButton" non trovato.');
        messaggioDiv.textContent = 'Errore: pulsante non trovato.';
    }
});

function goTo(url) {
	location.href = url;
}