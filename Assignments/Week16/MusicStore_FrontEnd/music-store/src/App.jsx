import { useEffect, useState } from 'react'

import './App.css'

function App() {
   //Api Url
   const Api_URL = "http://localhost:8080/music_store/store"
  const [store, setStore] = useState(0)

    //Api Pull function
    async function getStores() {
      const response = await fetch(Api_URL)
      const data = await response.json();
      setStore(data);
    };

    //Useeffect to populate the page on loadin
    useEffect(()=> {
      getStores()
    }, [])

    console.log(store);
    
  return (
    <>
  
      <div>
      <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Address</th>
          <th>City</th>
          <th>State</th>
          <th>Zip</th>
          <th>Phone</th>
        </tr>
      </thead>
      <tbody>
        {store.map((item) => (
          <tr key={item.storeId}>
            <td>{item.storeId}</td>
            <td>{item.storeName}</td>
            <td>{item.streetAddress}</td>
            <td>{item.city}</td>
            <td>{item.state}</td>
            <td>{item.zip}</td>
            <td>{item.phone}</td>
          </tr>
        ))}
      </tbody>
    </table>
      </div>
    </>
  )
}

export default App
