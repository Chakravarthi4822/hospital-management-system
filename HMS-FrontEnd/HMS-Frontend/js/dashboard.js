
async function loadStats(){
  const pairs=[["/patients","patientCount"],["/doctors","doctorCount"],["/appointments","appointmentCount"],["/bills","billCount"]];
  await Promise.all(pairs.map(async ([path,id])=>{
    try{ const data=await API.get(path); document.getElementById(id).textContent=Array.isArray(data)?data.length:"—"; }
    catch{ document.getElementById(id).textContent="—"; }
  }));
}
loadStats();
