const API="/api";
function saveUser(u){localStorage.setItem("smartparkUser",JSON.stringify(u));}
function getUser(){try{return JSON.parse(localStorage.getItem("smartparkUser"));}catch(e){return null;}}
function logout(){localStorage.removeItem("smartparkUser");location.href="/index.html";}
function requireLogin(){if(!getUser()){location.href="/login.html";return false;}return true;}
function requireAdmin(){const u=getUser();if(!u||u.role!=="ADMIN"){location.href="/admin/admin-login.html";return false;}return true;}
async function request(url,options={}){const r=await fetch(API+url,options);const text=await r.text();let data;try{data=text?JSON.parse(text):null}catch(e){data=text}if(!r.ok)throw new Error(typeof data==="string"?data:(data?.message||"Request failed"));return data;}
function escapeHtml(v){return String(v??"").replace(/[&<>'"]/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;',"'":'&#39;','"':'&quot;'}[c]));}
let allLocations=[];
async function loadLocations(targetId="locations"){const box=document.getElementById(targetId);if(!box)return;try{allLocations=await request("/locations");renderLocations(allLocations,targetId);}catch(e){box.innerHTML=`<div class="empty"><h3>Unable to load locations</h3><p>${escapeHtml(e.message)}</p></div>`;}}
function renderLocations(data,targetId="locations"){
 const box=document.getElementById(targetId);if(!box)return;
 if(!data.length){box.innerHTML=`<div class="empty"><div class="emoji">📍</div><h3>No matching parking locations</h3><p>Try another area or open Google Maps to explore nearby parking.</p></div>`;return;}
 const categories=[
  {re:/mall|shopping centre|shopping center|market|plaza/i,label:"MALL PARKING",icon:"🛍️",photo:"https://images.unsplash.com/photo-1519567241046-7f570eee3ce6?auto=format&fit=crop&w=1000&q=85",fallback:"/images/smart-parking-lot.png"},
  {re:/cafe|coffee|restaurant|food court|bakery/i,label:"CAFE PARKING",icon:"☕",photo:"https://images.unsplash.com/photo-1442512595331-e89e73853f31?auto=format&fit=crop&w=1000&q=85",fallback:"/images/smart-parking-lot.png"},
  {re:/temple|mandir|church|mosque|gurudwara|shrine/i,label:"TEMPLE / WORSHIP PARKING",icon:"🛕",photo:"https://images.unsplash.com/photo-1606293926075-69a00db12fcd?auto=format&fit=crop&w=1000&q=85",fallback:"/images/smart-parking-lot.png"},
  {re:/hospital|clinic|medical/i,label:"HOSPITAL PARKING",icon:"🏥",photo:"https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?auto=format&fit=crop&w=1000&q=85",fallback:"/images/smart-parking-lot.png"},
  {re:/station|railway|metro|bus depot|bus stand/i,label:"TRANSIT PARKING",icon:"🚉",photo:"https://images.unsplash.com/photo-1506521781263-d8422e82f27a?auto=format&fit=crop&w=1000&q=85",fallback:"/images/smart-parking-lot.png"},
  {re:/college|university|school|campus/i,label:"CAMPUS PARKING",icon:"🎓",photo:"https://images.unsplash.com/photo-1573348722427-f1d6819fdf98?auto=format&fit=crop&w=1000&q=85",fallback:"/images/smart-parking-lot.png"},
  {re:/office|business|corporate|tech park/i,label:"OFFICE PARKING",icon:"🏢",photo:"https://images.unsplash.com/photo-1590674899484-d5640e854abe?auto=format&fit=crop&w=1000&q=85",fallback:"/images/smart-parking-lot.png"}
 ];
 const defaultPhotos=[
  {label:"SMART PARKING",icon:"🅿️",photo:"/images/smart-parking-lot.png"},
  {label:"OPEN-AIR PARKING",icon:"🚘",photo:"https://images.unsplash.com/photo-1506521781263-d8422e82f27a?auto=format&fit=crop&w=1000&q=85"},
  {label:"COVERED PARKING",icon:"🚙",photo:"https://images.unsplash.com/photo-1590674899484-d5640e854abe?auto=format&fit=crop&w=1000&q=85"}
 ];
 box.innerHTML=data.map((x,i)=>{
  const text=`${x.locationName||""} ${x.description||""} ${x.address||""}`;
  const cat=categories.find(c=>c.re.test(text));
  const fallback=defaultPhotos[i%defaultPhotos.length];
  const photo=cat?.photo||fallback.photo;
  const label=cat?.label||fallback.label;
  const icon=cat?.icon||fallback.icon;
  const city=x.city||"Mumbai";
  return `<article class="card location-card"><div class="location-photo"><img src="${photo}" alt="${label.toLowerCase()} at ${escapeHtml(x.locationName)}" loading="lazy" onerror="this.onerror=null;this.src='${cat?.fallback||"/images/smart-parking-lot.png"}'"><span class="photo-label">${label} · ${escapeHtml(city)}</span></div><div class="location-top"><div><div class="location-icon location-category-icon">${icon}</div><h3>${escapeHtml(x.locationName)}</h3></div><span class="chip">${escapeHtml(city)}</span></div><p>${escapeHtml(x.address||"")}</p><p class="muted">${escapeHtml(x.description||`Parking available for ${label.toLowerCase().replace(" parking","")}.`)}</p><div class="chips"><span class="chip">🅿️ ${x.totalSlots||0} slots</span><span class="chip">⚡ Online booking</span></div><div class="actions"><a class="btn small" href="/slots.html?locationId=${x.locationId}">View Slots</a>${x.googleMapsUrl?`<a class="btn light small" target="_blank" rel="noopener" href="${escapeHtml(x.googleMapsUrl)}">🗺️ Google Maps</a>`:""}</div></article>`;
 }).join("");
}
function filterLocations(){const q=(document.getElementById("locationSearch")?.value||"").trim().toLowerCase();if(!q){renderLocations(allLocations);return;}renderLocations(allLocations.filter(x=>`${x.locationName} ${x.address} ${x.city} ${x.description}`.toLowerCase().includes(q)));}
function navActive(){const p=location.pathname.split('/').pop()||'index.html';document.querySelectorAll('.nav-links a[data-page]').forEach(a=>{if(a.dataset.page===p)a.classList.add('active')});}
function toggleMenu(){document.querySelector('.nav-links')?.classList.toggle('open');}
function commonInit(){navActive();const u=getUser();document.querySelectorAll('[data-user-name]').forEach(e=>e.textContent=u?.name||'Guest');}
document.addEventListener('DOMContentLoaded',()=>{commonInit();const reg=document.getElementById('registerForm');if(reg)reg.addEventListener('submit',async e=>{e.preventDefault();const d=Object.fromEntries(new FormData(reg).entries());d.vehicleNumber=(d.vehicleNumber||'').trim().toUpperCase();try{await request('/users/register',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(d)});reg.reset();reg.querySelector('.msg').textContent='Registration successful. Please login.';reg.querySelector('.msg').className='msg success-msg';}catch(x){reg.querySelector('.msg').textContent=x.message;}});const login=document.getElementById('loginForm');if(login)login.addEventListener('submit',async e=>{e.preventDefault();try{const u=await request('/users/login',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({email:login.email.value,password:login.password.value})});saveUser(u);location.href=u.role==='ADMIN'?'/admin/admin-dashboard.html':'/dashboard.html';}catch(x){login.querySelector('.msg').textContent=x.message;}});});
