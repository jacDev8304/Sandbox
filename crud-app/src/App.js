import './App.css';
import './Components/InputField';
// import TodoForm from './Components/TodoForm/TodoForm';

function App() {

  async function getTodos() {
    try {
      const response = await fetch("http://localhost:8080/todo", {
        method: "GET",
        headers: {
          "accept": "application/json",
        },
      })
      .then((response) => response.json());
  
      if (!response.ok) {
        throw new Error(`Error! status: ${response.status}`);
      }
  
      const result = await response.json();
      return result.json();
    } catch (err) {
      console.log(err);
    }
  }
  
  const todos = getTodos();

  console.log(todos);
  return (
    <div className="App">
     <p>
      {todos[0]}
      </p> 
    </div>
  );
}

export default App;
