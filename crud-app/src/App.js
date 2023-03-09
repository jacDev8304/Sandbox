import './App.css';
import './Components/InputField';
// import TodoForm from './Components/TodoForm/TodoForm';

function App() {
  let todos;

  async function getTodos() {
    try {

      const response = await fetch("http://localhost:8080/todo", {
        method: "GET",
        headers: {
          "accept": "application/json",
        },
      })
      .then((response) => response.json())
      .then((todo) => {
        todos = todo;
      });
  
      // if (!response.ok) {
      //   throw new Error(`Error! status: ${response.status}`);
      // }
  
      const result = await response.json();
      return result.json();
    } catch (err) {
      console.log(err);
    }
  }
  
  const test = getTodos();

  console.log('test',test);
  return (
    <div className="App">
     <p>
      {todos}
      </p> 
    </div>
  );
}

export default App;
