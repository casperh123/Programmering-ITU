export default function InputField({ name, value, setValue }) {
    
    function handleChange(event) {
        setValue(event.target.value)
    }

  return (
    <div class="column padding-xs fit-parent-width">
      <label>{name}</label>
      <input type="text" name={name} value={value} onChange={handleChange} class="input-field"/>
    </div>
  );
}