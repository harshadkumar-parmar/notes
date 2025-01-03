# async operations

### 1. `setImmediate()`
- **Purpose**: Executes a callback function on the next iteration of the event loop.
- **Use Case**: Useful for executing I/O callbacks after the current phase of the event loop.
- **Priority**: Lower than `process.nextTick` but higher than `setTimeout(0)`.

**Example**:
```javascript
setImmediate(() => {
  console.log('setImmediate');
});
console.log('Main Script Execution');
// Output Order: 'Main Script Execution' -> 'setImmediate'
```

### 2. `setTimeout()`
- **Purpose**: Executes a callback function after a specified delay (in milliseconds).
- **Use Case**: Useful for scheduling tasks to run after a delay.
- **Priority**: Lower than `setImmediate` and `process.nextTick`.

**Example**:
```javascript
setTimeout(() => {
  console.log('setTimeout');
}, 0);
console.log('Main Script Execution');
// Output Order: 'Main Script Execution' -> 'setTimeout'
```

### 3. `setInterval()`
- **Purpose**: Executes a callback function repeatedly at specified intervals (in milliseconds).
- **Use Case**: Useful for running recurring tasks.
- **Priority**: Each execution has the same priority as `setTimeout`.

**Example**:
```javascript
let count = 0;
const intervalId = setInterval(() => {
  console.log('setInterval');
  count++;
  if (count === 3) {
    clearInterval(intervalId);
  }
}, 1000);
console.log('Main Script Execution');
// Output Order: 'Main Script Execution' -> 'setInterval' (repeats every second)
```

### 4. `process.nextTick()`
- **Purpose**: Executes a callback function at the end of the current operation, before the next event loop tick.
- **Use Case**: Useful for deferring execution of a function until after the current operation completes.
- **Priority**: Higher than `setImmediate`, `setTimeout`, and `setInterval`.

**Example**:
```javascript
process.nextTick(() => {
  console.log('process.nextTick');
});
console.log('Main Script Execution');
// Output Order: 'Main Script Execution' -> 'process.nextTick'
```

### Priority Order
From highest to lowest priority:
1. **process.nextTick**
2. **setImmediate**
3. **setTimeout** and **setInterval** (with similar priority for timeouts of `0` ms)

### Summary Table

| Function          | Purpose                                          | Priority        | Example Output Order                    |
|-------------------|--------------------------------------------------|-----------------|-----------------------------------------|
| `process.nextTick`| Defer function until after current operation     | Highest         | 'Main Script Execution' -> 'process.nextTick' |
| `setImmediate`    | Execute function on the next event loop iteration| High            | 'Main Script Execution' -> 'setImmediate'     |
| `setTimeout`      | Execute function after specified delay           | Lower           | 'Main Script Execution' -> 'setTimeout' (if 0 ms delay) |
| `setInterval`     | Execute function repeatedly at specified intervals| Lower          | 'Main Script Execution' -> 'setInterval' (repeats) |