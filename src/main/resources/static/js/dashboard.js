const { fromEvent, of, from } = rxjs;
const { switchMap, take, catchError } = rxjs.operators;

document.addEventListener('DOMContentLoaded', () => {
    const rimuoviButton = document.getElementById('rimuoviButton');
    const messaggioDiv = document.getElementById('messaggio');

    if (rimuoviButton) {
        fromEvent(rimuoviButton, 'click').pipe(
            switchMap(() => {
                const idDaRimuovere = document.getElementById('id').value;

                if (!idDaRimuovere) {
                    console.error('ID non trovato nell\'attributo data-id.');
                    messaggioDiv.textContent = 'Errore: ID non trovato.';
                    return of(null); // Interrompi l'observable
                }

                const apiUrlWithQuery = `../../api/attivita/elimina?id=${idDaRimuovere}`;

                return from(fetch(apiUrlWithQuery, { method: 'DELETE' }).then(response => {
                    window.location.href = "../../app/attivita/rimuovi/success";
                }));
            }),
            take(1) // Esegui la chiamata solo una volta per click
        ).subscribe(response => {
            if (response !== null) {
                console.log('Attività rimossa con successo:', response);
                messaggioDiv.textContent = 'Attività rimossa con successo!';
                // Qui puoi aggiungere eventuali altre azioni dopo la rimozione
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